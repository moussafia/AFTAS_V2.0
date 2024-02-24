package ma.youcode.cmspringboot.web.dto.authDto;

import ma.youcode.cmspringboot.entity.AppUser;
import ma.youcode.cmspringboot.entity.Member;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public record RegisterRequestDto(
        @NotBlank(message = "firstname is required")
        String firstName,
        @NotBlank(message = "lastname is required")
        String lastName,
        @NotBlank(message = "username is required")
        String username,
        @NotBlank(message = "password is required")
        String password
) {
    public static Member toMember(RegisterRequestDto register){
        Member member = new Member().builder()
                .accessionDate(LocalDate.now())
                .build();
        member.setUsername(register.username());
        member.setName(register.firstName());
        member.setFamilyName(register.lastName());
        member.setPassword(register.password());
        return member;
    }

}
