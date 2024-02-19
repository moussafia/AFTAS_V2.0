package ma.youcode.cmspringboot.model.dto.rankingDto.rankingRequestDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record RankingSaveDto(
       @NotNull String codeCompetition,
        @NotNull @Min(1) Integer NumOfMember
) {

}
