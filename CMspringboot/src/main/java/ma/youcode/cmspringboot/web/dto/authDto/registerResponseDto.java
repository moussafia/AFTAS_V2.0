package ma.youcode.cmspringboot.web.dto.authDto;


public record registerResponseDto (
        String firstName,

        String LastName,
        String username,
        String message
) {
}