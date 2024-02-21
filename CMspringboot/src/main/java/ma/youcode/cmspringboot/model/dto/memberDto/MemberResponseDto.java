package ma.youcode.cmspringboot.model.dto.memberDto;

import ma.youcode.cmspringboot.model.entity.IdentityDocumentType;
import ma.youcode.cmspringboot.model.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
    LocalDate dateAccession
) {
    public static MemberResponseDto toMemberResponseDto(Member member){
        return  new MemberResponseDto(
                member.getName(), member.getFamilyName(), member.getIdentityDocumentType(),
                member.getIdentityNumber(), member.getNationality(), member.getNum(), member.getAccessionDate()
        );
}
}

