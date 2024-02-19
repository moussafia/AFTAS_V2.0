package ma.youcode.cmspringboot.model.dto.huntingDto.huntingRequestDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record HuntingInsertDto(
        @NotNull @Min(1) @NotBlank Long fish_id,
        @NotNull @Min(1) @NotBlank Integer average_weight,
        @NotNull @NotBlank String competition_code,
        @NotNull @Min(1) @NotBlank Integer member_num
        ) {
}
