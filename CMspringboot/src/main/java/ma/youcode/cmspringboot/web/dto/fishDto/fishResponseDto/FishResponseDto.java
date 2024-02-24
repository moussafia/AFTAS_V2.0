package ma.youcode.cmspringboot.web.dto.fishDto.fishResponseDto;

import ma.youcode.cmspringboot.entity.Fish;

public record FishResponseDto(
        Long id,
        String name
) {
    public static FishResponseDto toFishResponseDto(Fish fish){
        return new FishResponseDto(fish.getId(), fish.getName());
    }
}
