package ma.youcode.cmspringboot.service.aftas.serviceImpl;

import lombok.RequiredArgsConstructor;
import ma.youcode.cmspringboot.entity.AppRole;
import ma.youcode.cmspringboot.entity.AppRoleEnum;
import ma.youcode.cmspringboot.repository.RoleRepository;
import ma.youcode.cmspringboot.service.aftas.RoleService;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public AppRole findRoleByName(String role) throws NameNotFoundException {
        return roleRepository.findByName(AppRoleEnum.valueOf(role))
                .orElseThrow(()-> new NameNotFoundException("The role with name " +role + " does not exist"));
    }
}
