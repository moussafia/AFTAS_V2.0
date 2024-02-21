package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.model.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
    Optional<Level> findByCode(Integer code);
    Optional<Level> findByPoints(Integer point);
}
