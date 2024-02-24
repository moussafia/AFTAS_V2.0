package ma.youcode.cmspringboot.service.authentication;

import ma.youcode.cmspringboot.entity.AppUser;
import ma.youcode.cmspringboot.web.dto.authDto.AuthenticationRequestDto;
import ma.youcode.cmspringboot.web.dto.authDto.AuthenticationResponseDto;
import ma.youcode.cmspringboot.web.dto.authDto.RegisterRequestDto;

import javax.naming.NameNotFoundException;

public interface AuthService {
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);
    public void signUp(RegisterRequestDto registerRequestDto) throws NameNotFoundException;
    public AppUser me();
    public AppUser findByUsername(String username);
}
