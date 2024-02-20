package ma.youcode.cmspringboot.web.controller;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.model.dto.competitionDto.competionSaveDto.CompetitionSaveDto;
import ma.youcode.cmspringboot.model.dto.competitionDto.competitonResponseDto.CompetitionResponseDetailsDto;
import ma.youcode.cmspringboot.model.dto.competitionDto.competitonResponseDto.CompetitionResponseDto;
import ma.youcode.cmspringboot.service.CompetitionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/competition")
public class CompetitionController {
    private CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping
    public ResponseEntity<List<CompetitionResponseDto>> getAllCompetition(@RequestParam(defaultValue = "0")
                                                                          @Valid @Min(0) Integer page,
                                                                          @RequestParam(defaultValue = "6") @Min(1) Integer size,
                                                                          @RequestParam(defaultValue = "asc") @Pattern(regexp = "asc|desc" ,message = "invalid direction") String directionSort,
                                                                          @RequestParam(defaultValue = "date") String properties){
        Sort.Direction direction = Sort.Direction.fromString(directionSort);
        Sort sort = Sort.by(direction, properties);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Competition> competitionPage = competitionService.getAllCompetition(pageRequest);
        List<CompetitionResponseDto> competitionResponseDtoList = new ArrayList<>();
        competitionPage.forEach(cp->{
            competitionResponseDtoList.add(CompetitionResponseDto.toCompetitionResponseDto(cp));
        });
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("X-Total-Page", String.valueOf(competitionPage.getTotalPages()))
                .header("X-Total-Element", String.valueOf(competitionPage.getTotalElements()))
                .body(competitionResponseDtoList);

    }
    @PostMapping
    public ResponseEntity<CompetitionResponseDto> saveCompetition(@RequestBody
                                                                      CompetitionSaveDto competitionSaveDto){
        Competition competitionMapped = CompetitionSaveDto.toCompetition(competitionSaveDto);
        Competition competitionSaved = competitionService.createCompetition(competitionMapped);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(CompetitionResponseDto.toCompetitionResponseDto(competitionSaved));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CompetitionResponseDto>> getAllCompetitionFiltered(@RequestParam @NotNull boolean isClosed,
                                                                                  @RequestParam(defaultValue = "0") Integer page){
        PageRequest pageRequest = PageRequest.of(page, 6);
        Page<Competition> competitionPage = competitionService.filterCompetitionByDate(isClosed, pageRequest);
        List<CompetitionResponseDto> competitionResponseDtoList = new ArrayList<>();
        competitionPage.forEach(cp->{
            competitionResponseDtoList.add(CompetitionResponseDto.toCompetitionResponseDto(cp));
        });
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("X-Total-Page", String.valueOf(competitionPage.getTotalPages()))
                .body(competitionResponseDtoList);
    }
    @GetMapping("/competition/{code}")
        public ResponseEntity<CompetitionResponseDetailsDto> getCompetitionByCode(@PathVariable("code")
                                                                                @NotNull String competition_Code){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(CompetitionResponseDetailsDto
                        .toCompetitionResponseDetailsDto(competitionService.getCompetitionByCode(competition_Code)));
    }
    @GetMapping("/competition/dateNow")
    public ResponseEntity<CompetitionResponseDetailsDto> getCompetitionByCode(){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(CompetitionResponseDetailsDto
                        .toCompetitionResponseDetailsDto(competitionService.getCompetitionByDate())
                );
    }
}
