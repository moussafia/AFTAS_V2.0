package ma.youcode.cmspringboot.service.authentication.implementation;

import lombok.RequiredArgsConstructor;
import ma.youcode.cmspringboot.web.exception.CustomValidationException;
import ma.youcode.cmspringboot.entity.AppUser;
import ma.youcode.cmspringboot.repository.UserRepository;
import ma.youcode.cmspringboot.service.authentication.AppUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final UserRepository userRepository;
    @Override
    public AppUser getUserAuthenticated() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new CustomValidationException("email not found"));
    }

    @Override
    public AppUser getUserByNum(Integer num) {
        return userRepository.findByNum(num)
                .orElseThrow(()-> new CustomValidationException("no user found for the provided id"));
    }

    @Override
    public AppUser updateUser(AppUser user) {
        getUserByNum(user.getNum());
        return userRepository.save(user);    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("USER_NOT_FOUND"));
    }

}
