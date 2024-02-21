package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.model.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<AppRole, Long> {
}
