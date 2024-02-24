package ma.youcode.cmspringboot.web.dto.rankingDto;

import ma.youcode.cmspringboot.web.dto.competitionDto.competitonResponseDto.CompetitionResponseDto;
import ma.youcode.cmspringboot.web.dto.memberDto.MemberResponseDto;
import ma.youcode.cmspringboot.entity.Ranking;

public record RankingResponseDto(
        CompetitionResponseDto competitionResponseDto,
        MemberResponseDto memberResponseDto,
        int rank,
        int score
) {
    public static RankingResponseDto toRankingResponseDto(Ranking ranking){
        return new RankingResponseDto(
                CompetitionResponseDto.toCompetitionResponseDto(ranking.getCompetition()),
                MemberResponseDto.toMemberResponseDto(ranking.getMember()),
                ranking.getRank(),
                ranking.getScore()
        );
    }
}
