package ma.youcode.cmspringboot.web.controller;

import ma.youcode.cmspringboot.model.domain.Hunting;
import ma.youcode.cmspringboot.model.dto.huntingDto.huntingRequestDto.HuntingInsertDto;
import ma.youcode.cmspringboot.model.dto.huntingDto.huntingResponse.HuntingResponseDto;
import ma.youcode.cmspringboot.service.HuntingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hunting")
public class HuntingController {
    private HuntingService huntingService;

    public HuntingController(HuntingService huntingService) {
        this.huntingService = huntingService;
    }
    @PostMapping("/add")
    public ResponseEntity<HuntingResponseDto> addHuntingToMemberForCompetition(@RequestBody HuntingInsertDto huntingInsertDto){

        Hunting hunting = huntingService
                .insertHuntingForMemberInCompetition(huntingInsertDto.fish_id(),
                        huntingInsertDto.average_weight(),
                        huntingInsertDto.competition_code(),
                        huntingInsertDto.member_num());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(HuntingResponseDto.toHuntingResponseDto(hunting));
    }
}
