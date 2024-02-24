package ma.youcode.cmspringboot.web.dto.authDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AuthenticationResponseDto(
        Integer id,
        String firstName,

        String LastName,
        String username,
        List<String> roles,

        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("refresh_token")
        String refreshToken
) {
}
