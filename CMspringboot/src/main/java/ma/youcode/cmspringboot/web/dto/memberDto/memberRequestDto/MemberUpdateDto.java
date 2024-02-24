package ma.youcode.cmspringboot.web.dto.memberDto.memberRequestDto;

import ma.youcode.cmspringboot.entity.AppRole;
import ma.youcode.cmspringboot.entity.AppRoleEnum;
import ma.youcode.cmspringboot.entity.IdentityDocumentType;
import ma.youcode.cmspringboot.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

public record MemberUpdateDto(
        @NotBlank @NotNull
        Integer num,
        @NotBlank @NotNull

        String username,
        @NotBlank @NotNull
        String firstName,
        @NotBlank @NotNull

        String lastName,
        @NotBlank @NotNull

        IdentityDocumentType identityDocumentType,
        @NotBlank @NotNull

        String identityNumber,
        @NotBlank @NotNull

        String nationality,
        @NotBlank @NotNull
        boolean isAccountNonLocked,
        @NotBlank @NotNull
        Set<String> roles
) {
    public static Member toMember(MemberUpdateDto memberUpdateDto){
        Member member = new Member().builder()
                .identityNumber(memberUpdateDto.identityNumber)
                .identityDocumentType(memberUpdateDto.identityDocumentType)
                .nationality(memberUpdateDto.nationality)
                .build();
        member.setNum(memberUpdateDto.num);
        member.setName(memberUpdateDto.firstName);
        member.setFamilyName(memberUpdateDto.lastName);
        Set<AppRole> roles = memberUpdateDto.roles.stream()
                .map(r-> new AppRole(null, AppRoleEnum.valueOf(r), null))
                .collect(Collectors.toSet());
        member.setRoles(roles);
        member.setIsAccountNonLocked(memberUpdateDto.isAccountNonLocked);
        return member;
    }
}
