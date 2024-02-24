package ma.youcode.cmspringboot.web.dto.authDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record AccessTokenRequestDto(
        @NotBlank
        @NotNull(message = "refresh token is valid")
        String refreshToken
){}
