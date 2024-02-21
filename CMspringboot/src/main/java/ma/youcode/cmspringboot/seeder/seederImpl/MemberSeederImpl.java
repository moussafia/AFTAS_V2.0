package ma.youcode.cmspringboot.seeder.seederImpl;

import ma.youcode.cmspringboot.model.entity.IdentityDocumentType;
import ma.youcode.cmspringboot.model.entity.Member;
import ma.youcode.cmspringboot.repository.MemberRepository;
import ma.youcode.cmspringboot.seeder.MemberSeeder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
public class MemberSeederImpl implements MemberSeeder {
    private MemberRepository memberRepository;

    public MemberSeederImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> createMemberSeeder() {
        List<Member> members = List.of(
                new Member().builder().name("mohammed").familyName("moussafia").identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH212").accessionDate(LocalDate.now()).build(),
                new Member().builder().name("saad").familyName("moumou").identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH213").accessionDate(LocalDate.now()).build(),
                new Member().builder().name("khalid").familyName("fifel").identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH214").accessionDate(LocalDate.now()).build(),
                new Member().builder().name("oussama").familyName("khrabaq").identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH215").accessionDate(LocalDate.now()).build(),
                new Member().builder().name("maria").familyName("moussafia").identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH217").accessionDate(LocalDate.now()).build()
        );
        return this.memberRepository.saveAll(members);
    }
}
