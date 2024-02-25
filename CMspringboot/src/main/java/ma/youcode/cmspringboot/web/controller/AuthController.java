package ma.youcode.cmspringboot.web.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.cmspringboot.security.jwt.RefreshTokenService;
import ma.youcode.cmspringboot.web.dto.authDto.*;
import ma.youcode.cmspringboot.service.authentication.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<String>> register(@RequestBody RegisterRequestDto register)
            throws NameNotFoundException {
        authService.signUp(register);
        return ResponseEntity.ok(new ResponseDto<String>("Thank you for registration, wait the manager to approve you!", null));
    }
    @PostMapping("/logIn")
    public ResponseEntity<AuthenticationResponseDto> logIn(@RequestBody
                                                           @Valid AuthenticationRequestDto authenticationRequestDto){
        return ResponseEntity.ok(authService.authenticate(authenticationRequestDto));
    }
    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> getAccessTokenByRefreshToken(@RequestBody
                                                                            @Valid AccessTokenRequestDto refreshToken){
        return ResponseEntity.ok(refreshTokenService.generateAccessTokenByRefreshToken(refreshToken.refreshToken()));
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> me(){
        return ResponseEntity.ok(UserResponseDto.toMemberResponseDto(authService.me()));
    }
}
