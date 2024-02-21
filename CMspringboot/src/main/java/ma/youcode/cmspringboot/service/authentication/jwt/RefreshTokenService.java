package ma.youcode.cmspringboot.service.authentication.jwt;


import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;
import lombok.RequiredArgsConstructor;
import ma.youcode.cmspringboot.exception.TokenException;
import ma.youcode.cmspringboot.model.entity.AppRole;
import ma.youcode.cmspringboot.model.entity.AppUser;
import ma.youcode.cmspringboot.model.entity.RefreshToken;
import ma.youcode.cmspringboot.repository.RefreshTokenRepository;
import ma.youcode.cmspringboot.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JWTService jwtService;


    
    public Map<String, String> generateAccessAndRefreshToken(Authentication authentication,
                                                             List<AppRole> roles) {
        String subject = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Instant instant = Instant.now();
        Map<String, String> token = new HashMap<>();
        token.put("access_Token", jwtService.jwtAccessTokenEncoded(subject, instant, authorities, roles));
        token.put("refresh_Token", this.jwtRefreshTokenEncoded(subject, instant));
        return token;
    }

    
    public String jwtRefreshTokenEncoded(String subject, Instant instant){
        AppUser appUser =userRepository.findByUsername(subject)
                .orElseThrow(() ->  new RuntimeException("resources not found"));
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.UTC);
        OffsetDateTime plusOneYear = offsetDateTime.plusYears(1);
        Instant result = plusOneYear.toInstant();
        RefreshToken refreshToken = new RefreshToken()
                .builder()
                .refreshToken(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes()))
                .expiryDate(result)
                .revoked(false)
                .user(appUser)
                .build();
        RefreshToken refreshTokenSaved = this.refreshTokenRepository.save(refreshToken);
        return refreshTokenSaved.getRefreshToken();

    }
    
    public Map<String,String> generateAccessTokenByRefreshToken(String refreshToken){
        AppUser user = refreshTokenRepository.findByRefreshToken(refreshToken)
                .map(this::verifyExpiration)
                .map(this::verifyIsRevoked)
                .orElseThrow(()-> new RuntimeException("refresh token not found"))
                .getUser();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        String accessToken = jwtService.jwtAccessTokenEncoded(user.getUsername(), Instant.now(),
                authentication.getAuthorities(),user.getRoles().stream().toList());
        Map<String, String> token = new HashMap<>();
        token.put("access_Token", accessToken);
        token.put("refresh_Token", refreshToken);
        return token;
    }
    
    public RefreshToken verifyExpiration(RefreshToken refreshToken){
        if(refreshToken == null){
            throw new TokenException(null, "Token is null");
        }
        if(refreshToken.getExpiryDate().compareTo(Instant.now())<0){
            throw new TokenException(refreshToken.getRefreshToken(), "Refresh token was expired. Please make a new authentication request");
        }
        return refreshToken;
    }
    
    public RefreshToken verifyIsRevoked(RefreshToken refreshToken){
        if(refreshToken.isRevoked()){
            throw new TokenException(refreshToken.getRefreshToken(), "Refresh token was expired. Please make a new authentication request");
        }
        return refreshToken;
    }
}
