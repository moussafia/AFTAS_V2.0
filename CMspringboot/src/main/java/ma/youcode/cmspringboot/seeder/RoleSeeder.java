package ma.youcode.cmspringboot.seeder;

import ma.youcode.cmspringboot.entity.AppRole;

import java.util.List;
import java.util.Set;

public interface RoleSeeder {
    List<AppRole> createRoles();
    public Set<AppRole> getRoles();
}
