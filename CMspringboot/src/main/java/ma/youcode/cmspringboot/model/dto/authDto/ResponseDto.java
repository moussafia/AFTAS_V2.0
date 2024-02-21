package ma.youcode.cmspringboot.model.dto.authDto;


import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class ResponseDto<T> {
    private String message;
    private T result;
}
