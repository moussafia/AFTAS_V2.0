package ma.youcode.cmspringboot.seeder;

import ma.youcode.cmspringboot.entity.Fish;
import ma.youcode.cmspringboot.entity.Level;

import java.util.List;

public interface FishSeeder {
    public List<Fish> saveFish(List<Level> levelSet);
}
