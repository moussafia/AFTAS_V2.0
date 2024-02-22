package ma.youcode.cmspringboot.model.dto.authDto;

import ma.youcode.cmspringboot.model.entity.AppUser;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record RegisterRequestDto(
        @NotBlank(message = "firstname is required")
        String firstName,
        @NotBlank(message = "lastname is required")

        String lastName,
        @NotBlank(message = "email is required")

        String username,
        @NotBlank(message = "password is required")

        String password
) {
    public static AppUser toUser(RegisterRequestDto register){
        return new AppUser(null,register.username() , register.firstName(),
                register.firstName(),
                register.password(),
                null,
                false
                );
    }

}
