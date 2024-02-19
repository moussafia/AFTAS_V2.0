package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.model.domain.Fish;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {
    Optional<Fish> findByName(String name);
    Optional<Fish> findByNameAndIdNotLike(String name, Long id);
}
