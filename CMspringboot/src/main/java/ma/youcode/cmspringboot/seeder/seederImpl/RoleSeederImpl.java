package ma.youcode.cmspringboot.seeder.seederImpl;

import lombok.RequiredArgsConstructor;
import ma.youcode.cmspringboot.entity.AppRole;
import ma.youcode.cmspringboot.entity.AppRoleEnum;
import ma.youcode.cmspringboot.repository.RoleRepository;
import ma.youcode.cmspringboot.seeder.RoleSeeder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleSeederImpl implements RoleSeeder {
    private final RoleRepository roleRepository;
    @Override
    public List<AppRole> createRoles() {
        List<AppRole> rolesList = List.of(
                new AppRole(null, AppRoleEnum.JURY),
                new AppRole(null, AppRoleEnum.MEMBER),
                new AppRole(null, AppRoleEnum.MANAGER)
        );
        return roleRepository.saveAll(rolesList);
    }

    @Override
    public Set<AppRole> getRoles(){
        return new HashSet<>(roleRepository.findAll());
    }
}
