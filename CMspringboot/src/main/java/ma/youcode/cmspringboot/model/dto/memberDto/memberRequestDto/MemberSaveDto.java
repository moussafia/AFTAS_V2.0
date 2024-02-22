package ma.youcode.cmspringboot.model.dto.memberDto.memberRequestDto;

import ma.youcode.cmspringboot.model.entity.IdentityDocumentType;
import ma.youcode.cmspringboot.model.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record MemberSaveDto(
        @NotBlank @NotNull
        String firstName,
        @NotBlank @NotNull

        String lastName,
        @NotBlank @NotNull

        IdentityDocumentType identityDocumentType,
        @NotBlank @NotNull

        String identityNumber,
        @NotBlank @NotNull

        String nationality
) {
    public static Member toMember(MemberSaveDto memberSaveDto){
        Member member = new Member().builder()
                .identityNumber(memberSaveDto.identityNumber)
                .identityDocumentType(memberSaveDto.identityDocumentType)
                .nationality(memberSaveDto.nationality)
                .build();
        member.setName(memberSaveDto.firstName);
        member.setFamilyName(memberSaveDto.lastName);
        return member;
    }
}
