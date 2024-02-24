package ma.youcode.cmspringboot.service.authentication.implementation;
import lombok.RequiredArgsConstructor;
import ma.youcode.cmspringboot.entity.AppRole;
import ma.youcode.cmspringboot.entity.AppRoleEnum;
import ma.youcode.cmspringboot.entity.Member;
import ma.youcode.cmspringboot.security.util.SecurityUtil;
import ma.youcode.cmspringboot.service.aftas.RoleService;
import ma.youcode.cmspringboot.web.dto.authDto.AuthenticationRequestDto;
import ma.youcode.cmspringboot.web.dto.authDto.AuthenticationResponseDto;
import ma.youcode.cmspringboot.web.dto.authDto.RegisterRequestDto;
import ma.youcode.cmspringboot.entity.AppUser;
import ma.youcode.cmspringboot.repository.RoleRepository;
import ma.youcode.cmspringboot.repository.UserRepository;
import ma.youcode.cmspringboot.service.authentication.AuthService;
import ma.youcode.cmspringboot.security.jwt.RefreshTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import javax.validation.ValidationException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final RoleService roleService;


    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequestDto.username(), authenticationRequestDto.password())
        );
        var user = userRepository.findByUsername(authenticationRequestDto.username()).get();
        return generateAccessToken(authentication, user);
    }
    @Override
    public void signUp(RegisterRequestDto registerRequestDto) throws NameNotFoundException {
        validateUserIfExistForSignUp(registerRequestDto.username());
        String passwordEncrypted = passwordEncoder.encode(registerRequestDto.password());
        Member member = RegisterRequestDto.toMember(registerRequestDto);
        member.setPassword(passwordEncrypted);
        member.setAccountNonLocked(false);
        AppRole role = roleService.findRoleByName(AppRoleEnum.MEMBER.name());
        member.setRoles(Set.of(role));
        userRepository.save(member);
    }
    @Override
    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    @Override
    public AppUser me() {
        String username = SecurityUtil.getCurrentUsername();
        if(username == null)
            throw new BadCredentialsException("user not found");
        return this.findByUsername(username);
    }

    private void validateUserIfExistForSignUp(String username) {
        Optional<AppUser> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            throw new ValidationException("user with email "+ username +" already exist");
        }
    }
    private AuthenticationResponseDto generateAccessToken(Authentication authentication, AppUser user){
        Map<String, String> token = refreshTokenService.generateAccessAndRefreshToken(authentication,
                user.getRoles().stream().toList());
        return  new AuthenticationResponseDto(
                user.getNum(), user.getName(), user.getFamilyName(), user.getUsername(),
                user.getRoles().stream().map(r-> r.getName().name()).collect(Collectors.toList()),
                token.get("access_Token"), token.get("refresh_Token")
        );
    }
}
