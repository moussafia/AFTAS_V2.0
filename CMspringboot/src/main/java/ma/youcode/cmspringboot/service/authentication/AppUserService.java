package ma.youcode.cmspringboot.service.authentication;

import ma.youcode.cmspringboot.model.entity.AppUser;

public interface AppUserService {
    AppUser getUserAuthenticated();
    AppUser getUserByNum(Integer num);
    AppUser updateUser(AppUser user);
}
