package ma.youcode.cmspringboot.service.authentication;

import ma.youcode.cmspringboot.model.dto.authDto.AuthenticationRequestDto;
import ma.youcode.cmspringboot.model.dto.authDto.AuthenticationResponseDto;
import ma.youcode.cmspringboot.model.dto.authDto.RegisterRequestDto;

public interface AuthService {
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);
    public AuthenticationResponseDto signUp(RegisterRequestDto registerRequestDto);
}
