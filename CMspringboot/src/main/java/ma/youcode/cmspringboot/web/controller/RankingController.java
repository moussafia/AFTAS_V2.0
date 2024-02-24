package ma.youcode.cmspringboot.web.controller;

import ma.youcode.cmspringboot.entity.Ranking;
import ma.youcode.cmspringboot.web.dto.rankingDto.RankingResponseDto;
import ma.youcode.cmspringboot.web.dto.rankingDto.rankingRequestDto.RankingSaveDto;
import ma.youcode.cmspringboot.service.aftas.RankingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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
}
