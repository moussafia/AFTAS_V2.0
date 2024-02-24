package ma.youcode.cmspringboot.service.aftas;

import ma.youcode.cmspringboot.entity.AppRole;

import javax.naming.NameNotFoundException;
import java.util.Optional;

public interface RoleService {
    AppRole findRoleByName(String role) throws NameNotFoundException;
}
