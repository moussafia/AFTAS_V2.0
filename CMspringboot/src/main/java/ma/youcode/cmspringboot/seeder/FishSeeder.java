package ma.youcode.cmspringboot.seeder;

import ma.youcode.cmspringboot.model.domain.Fish;
import ma.youcode.cmspringboot.model.domain.Level;

import java.util.List;

public interface FishSeeder {
    public List<Fish> saveFish(List<Level> levelSet);
}
