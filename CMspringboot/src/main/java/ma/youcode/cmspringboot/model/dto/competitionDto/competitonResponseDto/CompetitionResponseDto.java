package ma.youcode.cmspringboot.model.dto.competitionDto.competitonResponseDto;

import ma.youcode.cmspringboot.model.entity.Competition;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionResponseDto(
        String code,
        LocalDate date,
        LocalTime startDate,
        LocalTime endTime,
        Integer numberOfParticipants,
        String location,
        Float amount
) {
    public static CompetitionResponseDto toCompetitionResponseDto(Competition competition){
        return new CompetitionResponseDto(competition.getCode(),
                competition.getDate(), competition.getStartDate(), competition.getEndTime(),
                competition.getNumberOfParticipants(), competition.getLocation(),
                competition.getAmount());
    }
}
