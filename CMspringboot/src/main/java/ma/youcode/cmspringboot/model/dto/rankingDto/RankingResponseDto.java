package ma.youcode.cmspringboot.model.dto.rankingDto;

import ma.youcode.cmspringboot.model.domain.Ranking;
import ma.youcode.cmspringboot.model.dto.competitionDto.competitonResponseDto.CompetitionResponseDto;
import ma.youcode.cmspringboot.model.dto.memberDto.MemberResponseDto;

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
