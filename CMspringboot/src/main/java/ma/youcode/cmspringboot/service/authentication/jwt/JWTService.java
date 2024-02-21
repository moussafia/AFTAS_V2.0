package ma.youcode.cmspringboot.service.authentication.jwt;

import lombok.RequiredArgsConstructor;
import ma.youcode.cmspringboot.model.entity.AppRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class JWTService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    @Value("${token.date-expiration}")
    private long dateExpirationAccessToken;

    
    public String jwtAccessTokenEncoded(String subject,
                                        Instant instant,
                                        Collection<? extends GrantedAuthority> authorities,
                                        List<AppRole> roles){
        String scope = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        String role = roles.stream()
                .map(AppRole::getName)
                .collect(Collectors.joining(" "));
        return jwtEncoder
                .encode(JwtEncoderParameters.from(buildToken(subject, instant, scope, role)))
                .getTokenValue();
    }
    
    public String extractUserName(String token){
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject();
    }

    
    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String emailUser = extractUserName(jwt);
        return (emailUser.equals(userDetails.getUsername()))
                && !isTokenExpired(jwt);
    }
    private boolean isTokenExpired(String token) {
        Jwt jwt= jwtDecoder.decode(token);
        final Instant dateExpiration = jwt.getExpiresAt();
        return dateExpiration.isBefore(Instant.now());
    }
    private JwtClaimsSet buildToken(String subject,
                                    Instant instant,
                                    String scope,
                                    String role){
        return JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(this.dateExpirationAccessToken, ChronoUnit.MINUTES))
                .issuer("task-service")
                .claim("scope",scope)
                .claim("roles",role)
                .claim("type_token","ACCESS_TOKEN")
                .build();
    }
}
