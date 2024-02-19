package ma.youcode.cmspringboot.service;

import ma.youcode.cmspringboot.model.domain.Fish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FishService {
    Fish createFish(Fish fish);
    public Fish updateFish(Fish fish);
    public Fish getFishById(Long fishId);
    Fish findFishByName(String fishName);
    List<Fish> getAllFish();
}
