package ma.youcode.cmspringboot.web.controller;

import ma.youcode.cmspringboot.entity.Competition;
import ma.youcode.cmspringboot.entity.Ranking;
import ma.youcode.cmspringboot.web.dto.competitionDto.competitonResponseDto.CompetitionResponseDto;
import ma.youcode.cmspringboot.web.dto.rankingDto.RankingResponseDto;
import ma.youcode.cmspringboot.web.dto.rankingDto.rankingRequestDto.RankingSaveDto;
import ma.youcode.cmspringboot.service.aftas.RankingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ranking")

public class RankingController {
    private RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }
    @PostMapping("/inscribe")
    public ResponseEntity<RankingResponseDto> InscribeMemberInCompetition(@RequestBody RankingSaveDto rankingSaveDto){
        Ranking ranking = rankingService.InscribeMemberToCompetition(rankingSaveDto.NumOfMember()
                , rankingSaveDto.codeCompetition());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RankingResponseDto.toRankingResponseDto(ranking));
    }
    @GetMapping("Rank/{competition_code}")
    public ResponseEntity<List<RankingResponseDto>> getRankingFromCompetition(@PathVariable("competition_code")
                                                                        @NotNull String competition_code){
        List<RankingResponseDto> rankingResponseDtoList = new ArrayList<>();
        rankingService.createRankingForCompetition(competition_code)
                .forEach(ranking -> {
                    rankingResponseDtoList
                            .add(RankingResponseDto.toRankingResponseDto(ranking));
                });
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(rankingResponseDtoList);
    }

    @GetMapping("Rank/podium/{competition_code}")
    public ResponseEntity<List<RankingResponseDto>> getPodium(@PathVariable("competition_code")
                                                              @NotNull String competition_code){
        List<RankingResponseDto> rankingResponseDtoList = new ArrayList<>();
        rankingService.getPodium(competition_code)
                .forEach(ranking -> {
                    rankingResponseDtoList
                            .add(RankingResponseDto.toRankingResponseDto(ranking));
                });
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(rankingResponseDtoList);
    }
    @GetMapping("/myCompetition")
    public ResponseEntity<List<CompetitionResponseDto>> getMyCompetition(@RequestParam(defaultValue = "0")
                                                                          @Valid @Min(0) Integer page,
                                                                          @RequestParam(defaultValue = "6") @Min(1) Integer size,
                                                                          @RequestParam(defaultValue = "asc") @Pattern(regexp = "asc|desc" ,message = "invalid direction") String directionSort,
                                                                          @RequestParam(defaultValue = "date") String properties){
        Sort.Direction direction = Sort.Direction.fromString(directionSort);
        Sort sort = Sort.by(direction, properties);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Competition> competitionPage = rankingService.getMyCompetition(pageRequest);
        List<CompetitionResponseDto> competitionResponseDtoList = new ArrayList<>();
        competitionPage.forEach(cp->{
            competitionResponseDtoList.add(CompetitionResponseDto.toCompetitionResponseDto(cp));
        });
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("X-Total-Page", String.valueOf(competitionPage.getTotalPages()))
                .header("X-Total-Element", String.valueOf(competitionPage.getTotalElements()))
                .body(competitionResponseDtoList);

    }
}
