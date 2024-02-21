package ma.youcode.cmspringboot.seeder.seederImpl;

import ma.youcode.cmspringboot.model.entity.Level;
import ma.youcode.cmspringboot.repository.LevelRepository;
import ma.youcode.cmspringboot.seeder.LevelSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LevelSeederImpl implements LevelSeeder {
    private LevelRepository levelRepository;

    @Autowired
    public LevelSeederImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    public LevelSeederImpl() {
    }

    @Override
    public List<Level> saveLevel(){
        Level level1 = new Level().builder().code(1).description("Easy").points(3).build();
        Level level2 = new Level().builder().code(2).description("Fairly easy").points(5).build();
        Level level3 = new Level().builder().code(3).description("Moderate").points(7).build();
        Level level4 = new Level().builder().code(4).description("Fairly difficult").points(11).build();
        Level level5 = new Level().builder().code(5).description("Difficult ").points(13).build();
        List<Level> levelList = List.of(level1, level2, level3, level4, level5);
        return levelRepository.saveAll(levelList);
    }
}
