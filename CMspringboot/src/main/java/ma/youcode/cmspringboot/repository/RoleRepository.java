package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.entity.AppRole;
import ma.youcode.cmspringboot.entity.AppRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<AppRole, Long> {
    Optional<AppRole> findByName(AppRoleEnum roleName);
}
