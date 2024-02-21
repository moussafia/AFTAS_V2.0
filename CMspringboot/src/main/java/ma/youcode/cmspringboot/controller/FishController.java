package ma.youcode.cmspringboot.controller;

import ma.youcode.cmspringboot.model.dto.fishDto.fishResponseDto.FishResponseDto;
import ma.youcode.cmspringboot.service.aftas.FishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/fish")
public class FishController {
    private FishService fishService;

    public FishController(FishService fishService) {
        this.fishService = fishService;
    }
    @GetMapping
    public ResponseEntity<List<FishResponseDto>> getAllFish(){
        List<FishResponseDto> fishResponseDtos = new ArrayList<>();
        this.fishService.getAllFish()
                .forEach( f -> fishResponseDtos.add(FishResponseDto.toFishResponseDto(f)));
        return ResponseEntity.ok().body(fishResponseDtos);
    }
}
