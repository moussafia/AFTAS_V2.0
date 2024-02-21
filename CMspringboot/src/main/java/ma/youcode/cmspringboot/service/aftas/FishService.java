package ma.youcode.cmspringboot.service.aftas;

import ma.youcode.cmspringboot.model.entity.Fish;

import java.util.List;

public interface FishService {
    Fish createFish(Fish fish);
    public Fish updateFish(Fish fish);
    public Fish getFishById(Long fishId);
    Fish findFishByName(String fishName);
    List<Fish> getAllFish();
}
