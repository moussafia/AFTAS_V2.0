package ma.youcode.cmspringboot.service.authentication;

import lombok.RequiredArgsConstructor;
import ma.youcode.cmspringboot.model.entity.AppUser;
import ma.youcode.cmspringboot.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("user with email "+ username + " not found"));
        Collection<? extends GrantedAuthority> authorities = appUser.getRoles().stream()
                .map(p->new SimpleGrantedAuthority(p.getName()))
                .collect(Collectors.toSet());
        return new User(username, appUser.getPassword(), authorities);
    }
}
