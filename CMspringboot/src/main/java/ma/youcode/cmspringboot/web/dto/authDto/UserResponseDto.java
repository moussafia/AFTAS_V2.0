package ma.youcode.cmspringboot.web.dto.authDto;

import ma.youcode.cmspringboot.entity.AppUser;
import java.util.Set;
import java.util.stream.Collectors;

public record UserResponseDto(
        String firstName,

        String lastName,
        Integer number,
        Set<String> roles,
        String username
) {
public static UserResponseDto toMemberResponseDto(AppUser user){
        Set<String> roles = user.getRoles()
        .stream().map(r-> String.valueOf(r.getName()))
        .collect(Collectors.toSet());
        return  new UserResponseDto(
        user.getName(), user.getFamilyName(),
         user.getNum(),
        roles, user.getUsername());
        }
}
