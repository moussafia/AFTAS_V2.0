package ma.youcode.cmspringboot.model.dto.huntingDto.huntingResponse;

import ma.youcode.cmspringboot.model.domain.Fish;
import ma.youcode.cmspringboot.model.domain.Hunting;
import ma.youcode.cmspringboot.model.dto.competitionDto.competitonResponseDto.CompetitionResponseDto;
import ma.youcode.cmspringboot.model.dto.memberDto.MemberResponseDto;

public record HuntingResponseDto(
        Fish fish,
        CompetitionResponseDto competitionResponseDto,
        MemberResponseDto memberResponseDto,
        Integer numberOfHunting
) {
    public static HuntingResponseDto toHuntingResponseDto(Hunting hunting){
        return new HuntingResponseDto(hunting.getFish(),
                CompetitionResponseDto.toCompetitionResponseDto(hunting.getCompetition()),
                MemberResponseDto.toMemberResponseDto(hunting.getMember()),
                hunting.getNumberOfFish()
                );
    }
}
