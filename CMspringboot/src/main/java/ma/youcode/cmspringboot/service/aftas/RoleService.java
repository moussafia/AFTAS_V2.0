package ma.youcode.cmspringboot.service.aftas;

import ma.youcode.cmspringboot.entity.AppRole;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.Optional;
@Service
public interface RoleService {
    AppRole findRoleByName(String role) throws NameNotFoundException;
}
