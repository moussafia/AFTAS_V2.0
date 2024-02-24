package ma.youcode.cmspringboot.web.dto.memberDto;

import ma.youcode.cmspringboot.entity.AppRole;
import ma.youcode.cmspringboot.entity.IdentityDocumentType;
import ma.youcode.cmspringboot.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public record MemberResponseDto(
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
    Integer number,
    LocalDate dateAccession,
    Set<String> roles
) {
    public static MemberResponseDto toMemberResponseDto(Member member){
        Set<String> roles = member.getRoles()
                .stream().map(r-> String.valueOf(r.getName()))
                .collect(Collectors.toSet());
        return  new MemberResponseDto(
                member.getName(), member.getFamilyName(),
                member.getIdentityDocumentType(),
                member.getIdentityNumber(),
                member.getNationality(), member.getNum(),
                member.getAccessionDate(),
                roles
                );
}
}

