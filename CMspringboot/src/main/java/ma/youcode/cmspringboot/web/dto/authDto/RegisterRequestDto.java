package ma.youcode.cmspringboot.web.dto.authDto;

import ma.youcode.cmspringboot.entity.AppUser;

import javax.validation.constraints.NotBlank;

public record RegisterRequestDto(
        @NotBlank(message = "firstname is required")
        String firstName,
        @NotBlank(message = "lastname is required")
        String lastName,
        @NotBlank(message = "username is required")
        String username,
        @NotBlank(message = "password is required")
        String password
) {
    public static AppUser toMember(RegisterRequestDto register){
        return new AppUser(null,register.username() ,
                register.firstName(),
                register.lastName(),
                register.password(),
                null,
                false
                );
    }

}
