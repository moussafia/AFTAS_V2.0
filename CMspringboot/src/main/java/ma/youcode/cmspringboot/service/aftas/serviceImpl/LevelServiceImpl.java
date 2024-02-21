package ma.youcode.cmspringboot.service.aftas.serviceImpl;

import ma.youcode.cmspringboot.model.domain.Level;
import ma.youcode.cmspringboot.repository.LevelRepository;
import ma.youcode.cmspringboot.service.aftas.LevelService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LevelServiceImpl implements LevelService {
    private LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Level createLevel(Level level) {
        validatePointLevelIfAlreadyExistForCreate(level);
        createPointForLevel(level, true);
    return levelRepository.findByPoints(level.getPoints()).orElse(null);
    }

    @Override
    public List<Level> getLevels() {
        return levelRepository.findAll();
    }

    @Override
    public Optional<Level> searchLevel(Integer code) {
        return levelRepository.findByCode(code);
    }

    @Override
    public Level updateLevel(Level level) {
        checkIfCodeNotExistForUpdate(level);
        createPointForLevel(level, false);
        return levelRepository.findByPoints(level.getPoints()).orElse(null);
    }

    @Override
    public Level getLevelByCode(Level level) {
        return levelRepository.findByCode(level.getCode())
                .orElseThrow(() -> new IllegalStateException("Level with code " + level.getCode() + " not found"));
    }

    public void createPointForLevel(Level level, boolean isForCreate){
        List<Level> levelList = levelRepository.findAll();
        if(isForCreate) levelList.add(level);
        else levelList.set(level.getCode() - 1, level);
        levelRepository.saveAll(
                levelList.stream()
                        .sorted(Comparator.comparing(Level::getPoints))
                        .peek(l-> l.setCode(levelList.indexOf(l))).collect(Collectors.toList())
        );
    }
    public void checkIfCodeNotExistForUpdate(Level level){
        Level levelFound = levelRepository.findByCode(level.getCode()).orElse(null);
        if (levelFound == null)
            throw new IllegalStateException("this " + levelFound.getCode() + "is not exist");
    }

    public void validatePointLevelIfAlreadyExistForCreate(Level level){
        levelRepository.findByPoints(level.getPoints()).ifPresent(l->{
            throw new IllegalStateException(l.getPoints() + " Already exist for level " + l.getCode());
        });
    }


}
