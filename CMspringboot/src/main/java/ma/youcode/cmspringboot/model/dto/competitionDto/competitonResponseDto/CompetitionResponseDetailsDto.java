package ma.youcode.cmspringboot.model.dto.competitionDto.competitonResponseDto;

import ma.youcode.cmspringboot.model.entity.Competition;
import ma.youcode.cmspringboot.model.dto.rankingDto.RankingResponseDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public record CompetitionResponseDetailsDto (
        String code,
        LocalDate date,
        LocalTime startDate,
        LocalTime endTime,
        Integer numberOfParticipants,
        String location,
        Float amount,
        Set<RankingResponseDto> rankingList
) {
    public static CompetitionResponseDetailsDto toCompetitionResponseDetailsDto(Competition competition) {
        Set<RankingResponseDto> rankingResponseDto = new HashSet<>();
        competition.getRanking().forEach(cr->{
            rankingResponseDto.add(RankingResponseDto.toRankingResponseDto(cr));
        });
        return new CompetitionResponseDetailsDto(competition.getCode(),
                competition.getDate(), competition.getStartDate(), competition.getEndTime(),
                competition.getNumberOfParticipants(), competition.getLocation(),
                competition.getAmount(), rankingResponseDto);
    }
}