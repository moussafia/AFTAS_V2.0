package ma.youcode.cmspringboot.service.authentication.implementation;
import ma.youcode.cmspringboot.model.dto.authDto.AuthenticationRequestDto;
import ma.youcode.cmspringboot.model.dto.authDto.AuthenticationResponseDto;
import ma.youcode.cmspringboot.model.dto.authDto.RegisterRequestDto;
import ma.youcode.cmspringboot.model.entity.AppRole;
import ma.youcode.cmspringboot.model.entity.AppUser;
import ma.youcode.cmspringboot.repository.RoleRepository;
import ma.youcode.cmspringboot.repository.UserRepository;
import ma.youcode.cmspringboot.service.authentication.AuthService;
import ma.youcode.cmspringboot.service.authentication.jwt.RefreshTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.validation.ValidationException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private RefreshTokenService refreshTokenService;
    private RoleRepository roleRepository;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           RefreshTokenService refreshTokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequestDto.username(), authenticationRequestDto.password())
        );
        var user = userRepository.findByUsername(authenticationRequestDto.username()).get();
        return generateAccessToken(authentication, user);
    }
    @Override
    public AuthenticationResponseDto signUp(RegisterRequestDto registerRequestDto){
        validateUserIfExistForSignUp(registerRequestDto.username());
        String passwordEncrypted = passwordEncoder.encode(registerRequestDto.password());
        AppUser appUser = RegisterRequestDto.toUser(registerRequestDto);
        appUser.setPassword(passwordEncrypted);
        var userSaved = userRepository.save(appUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved.getUsername(), userSaved.getPassword());
        return generateAccessToken(authentication, userSaved);
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
                user.getRoles().stream().map(AppRole::getName).collect(Collectors.toList()),
                token.get("access_Token"), token.get("refresh_Token")
        );
    }
}
