package ma.youcode.cmspringboot.model.dto.fishDto.fishResponseDto;

import ma.youcode.cmspringboot.model.entity.Fish;

public record FishResponseDto(
        Long id,
        String name
) {
    public static FishResponseDto toFishResponseDto(Fish fish){
        return new FishResponseDto(fish.getId(), fish.getName());
    }
}
