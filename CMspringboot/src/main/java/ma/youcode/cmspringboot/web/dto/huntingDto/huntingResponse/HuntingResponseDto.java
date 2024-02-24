package ma.youcode.cmspringboot.web.dto.huntingDto.huntingResponse;

import ma.youcode.cmspringboot.web.dto.competitionDto.competitonResponseDto.CompetitionResponseDto;
import ma.youcode.cmspringboot.web.dto.memberDto.MemberResponseDto;
import ma.youcode.cmspringboot.entity.Fish;
import ma.youcode.cmspringboot.entity.Hunting;

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
