package ma.youcode.cmspringboot.service;

import ma.youcode.cmspringboot.model.domain.Level;

import java.util.List;
import java.util.Optional;

public interface LevelService {
    Level createLevel(Level level);
    List<Level> getLevels();
    Optional<Level> searchLevel(Integer code);
    Level updateLevel(Level level);
    Level getLevelByCode(Level level);
}
