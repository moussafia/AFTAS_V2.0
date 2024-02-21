package ma.youcode.cmspringboot.seeder.seederImpl;

import ma.youcode.cmspringboot.model.entity.Fish;
import ma.youcode.cmspringboot.model.entity.Level;
import ma.youcode.cmspringboot.repository.FishRepository;
import ma.youcode.cmspringboot.seeder.FishSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FishSeederImpl implements FishSeeder {
    private FishRepository fishRepository;
    @Autowired
    public FishSeederImpl(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }

    public FishSeederImpl() {
    }

    @Override
    public List<Fish> saveFish(List<Level> levelSet) {
        List<Fish> fishList = new ArrayList<>();
        fishList.add(Fish.builder().name("Bass").averageWeight(3.0f).level(levelSet.get(1)).build());
        fishList.add(Fish.builder().name("Tuna").averageWeight(1.0f).level(levelSet.get(0)).build());
        fishList.add(Fish.builder().name("Cod").averageWeight(8.0f).level(levelSet.get(2)).build());
        fishList.add(Fish.builder().name("Mackerel").averageWeight(5.0f).level(levelSet.get(1)).build());
        fishList.add(Fish.builder().name("Pike").averageWeight(0.5f).level(levelSet.get(0)).build());
        fishList.add(Fish.builder().name("Perch").averageWeight(10.0f).level(levelSet.get(2)).build());
        fishList.add(Fish.builder().name("Catfish").averageWeight(2.0f).level(levelSet.get(1)).build());
        fishList.add(Fish.builder().name("Salmon").averageWeight(10.0f).level(levelSet.get(2)).build());
        fishList.add(Fish.builder().name("Trout").averageWeight(15.0f).level(levelSet.get(3)).build());
        fishList.add(Fish.builder().name("Carp").averageWeight(2.0f).level(levelSet.get(1)).build());
        fishList.add(Fish.builder().name("Swordfish").averageWeight(20.0f).level(levelSet.get(4)).build());
        fishList.add(Fish.builder().name("Marlin").averageWeight(2.0f).level(levelSet.get(2)).build());
        fishList.add(Fish.builder().name("Barracuda").averageWeight(3.0f).level(levelSet.get(1)).build());
        fishList.add(Fish.builder().name("Mahi-Mahi").averageWeight(5.0f).level(levelSet.get(2)).build());
        fishList.add(Fish.builder().name("Bluefish").averageWeight(15.0f).level(levelSet.get(1)).build());
        fishList.add(Fish.builder().name("Walleye").averageWeight(10.0f).level(levelSet.get(1)).build());
        fishList.add(Fish.builder().name("Snapper").averageWeight(200.0f).level(levelSet.get(1)).build());
        fishList.add(Fish.builder().name("Grouper").averageWeight(150.0f).level(levelSet.get(2)).build());
        fishList.add(Fish.builder().name("Haddock").averageWeight(5.0f).level(levelSet.get(1)).build());
        fishList.add(Fish.builder().name("Shark").averageWeight(50.0f).level(levelSet.get(4)).build());
        fishList.add(Fish.builder().name("Eel").averageWeight(2.0f).level(levelSet.get(1)).build());

        return fishRepository.saveAll(fishList);
    }
}
