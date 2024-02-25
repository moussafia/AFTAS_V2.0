package ma.youcode.cmspringboot.security.util;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class CustomError {
    private final String field;
    private final String message;
}
