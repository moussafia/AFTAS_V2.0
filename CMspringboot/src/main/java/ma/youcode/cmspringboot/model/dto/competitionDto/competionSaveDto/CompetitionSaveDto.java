package ma.youcode.cmspringboot.model.dto.competitionDto.competionSaveDto;

import ma.youcode.cmspringboot.model.domain.Competition;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionSaveDto(
    @NotNull
    @Pattern(regexp = "yyyy-MM-dd")
    LocalDate date,
    @NotNull
    LocalTime startTime,
    @NotNull
    LocalTime endTime,
    @NotBlank @NotNull

    @Size(min = 3, max = 200, message = "length is too height or too low")
    String location,
     @Valid
    @NotBlank @NotNull

    @Min(1)
    Integer numberOfParticipants,
    @NotBlank @NotNull

    @PositiveOrZero
    Float amount){
    public static Competition toCompetition(CompetitionSaveDto competitionSaveDto) {
        return new Competition().builder()
                .date(competitionSaveDto.date)
                .amount(competitionSaveDto.amount)
                .startDate(competitionSaveDto.startTime)
                .endTime(competitionSaveDto.endTime)
                .location(competitionSaveDto.location)
                .numberOfParticipants(competitionSaveDto.numberOfParticipants)
                .build();
    }
}
