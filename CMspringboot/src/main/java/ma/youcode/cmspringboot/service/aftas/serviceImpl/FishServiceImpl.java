package ma.youcode.cmspringboot.service.aftas.serviceImpl;

import ma.youcode.cmspringboot.model.domain.Fish;
import ma.youcode.cmspringboot.repository.FishRepository;
import ma.youcode.cmspringboot.service.aftas.FishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishServiceImpl implements FishService {
    private FishRepository fishRepository;

    public FishServiceImpl(FishRepository fishRepository) {

        this.fishRepository = fishRepository;
    }

    @Override
    public Fish createFish(Fish fish) {
        validateIfAlreadyExistForCreate(fish);
        return this.fishRepository.save(fish);
    }
    @Override
    public Fish updateFish(Fish fish) {
        validateIfExistForUpdate(fish);
        validateNameIfExistForUpdate(fish);
        return this.fishRepository.save(fish);
    }
    @Override
    public Fish getFishById(Long fishId) {
        return fishRepository.findById(fishId)
                .orElseThrow(() -> new IllegalStateException("Fish with ID " + fishId + " not found"));
    }

    @Override
    public Fish findFishByName(String fishName) {

        return fishRepository.findByName(fishName).orElse(null);
    }

    @Override
    public List<Fish> getAllFish() {

        return fishRepository.findAll();
    }
    void validateIfAlreadyExistForCreate(Fish fish){
        fishRepository.findByName(fish.getName()).ifPresent(f-> {
            throw new IllegalStateException("this "+f.getName()+" already exist");
        });
    }
    void validateIfExistForUpdate(Fish fish){
        Fish fishExited = fishRepository.findById(fish.getId()).orElse(null);
        if (fishExited == null)
            throw new IllegalStateException("The fish with name '" + fish.getId() + "' does not exist for updating.");
    }
    void validateNameIfExistForUpdate(Fish fish){
        Fish fishExited = fishRepository.findByNameAndIdNotLike(fish.getName(), fish.getId()).orElse(null);
        if (fishExited != null)
            throw new IllegalStateException("The fish with name '" + fish.getName() + "' already exist");
    }
}
