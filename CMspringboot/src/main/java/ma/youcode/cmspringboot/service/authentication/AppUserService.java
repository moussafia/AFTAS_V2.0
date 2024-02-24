package ma.youcode.cmspringboot.service.authentication;

import ma.youcode.cmspringboot.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService {
    AppUser getUserAuthenticated();
    AppUser getUserByNum(Integer num);
    AppUser updateUser(AppUser user);
    public UserDetailsService userDetailsService();

}
