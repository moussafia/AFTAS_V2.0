package ma.youcode.cmspringboot.seeder.seederImpl;

import lombok.RequiredArgsConstructor;
import ma.youcode.cmspringboot.entity.AppRole;
import ma.youcode.cmspringboot.entity.AppRoleEnum;
import ma.youcode.cmspringboot.repository.RoleRepository;
import ma.youcode.cmspringboot.seeder.RoleSeeder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleSeederImpl implements RoleSeeder {
    private final RoleRepository roleRepository;
    @Override
    public List<AppRole> createRoles() {
        List<AppRole> rolesList = List.of(
                new AppRole(null, AppRoleEnum.JURY, null),
                new AppRole(null, AppRoleEnum.MEMBER, null),
                new AppRole(null, AppRoleEnum.MANAGER, null)
        );
        return roleRepository.saveAll(rolesList);
    }
}
