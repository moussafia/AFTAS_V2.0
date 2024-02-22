package ma.youcode.cmspringboot.model.dto.authDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AuthenticationRequestDto(
        @NotBlank(message = "Email cannot be blank")
        String username,
        @NotBlank(message = "Email cannot be blank")
        @Size(min = 2, message = "Password must be at least 2 characters long")
        String password
) {
}
