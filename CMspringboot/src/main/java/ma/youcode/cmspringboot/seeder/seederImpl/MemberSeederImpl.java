package ma.youcode.cmspringboot.seeder.seederImpl;

import lombok.RequiredArgsConstructor;
import ma.youcode.cmspringboot.model.entity.AppUser;
import ma.youcode.cmspringboot.model.entity.IdentityDocumentType;
import ma.youcode.cmspringboot.model.entity.Member;
import ma.youcode.cmspringboot.repository.MemberRepository;
import ma.youcode.cmspringboot.seeder.MemberSeeder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
@RequiredArgsConstructor
public class MemberSeederImpl implements MemberSeeder {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Member> createMemberSeeder() {
        Member member1 = new Member().builder().identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH212").nationality("morocco").accessionDate(LocalDate.now()).build();
        member1.setUsername("m_moussafia"); member1.setFamilyName("moussafia");member1.setName("mohammed");member1.setPassword(passwordEncoder.encode("1234"));
        Member member2 = new Member().builder().identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH213").nationality("morocco").accessionDate(LocalDate.now()).build();
        member2.setUsername("k_fifel"); member2.setFamilyName("khalid");member2.setName("fifel");member2.setPassword(passwordEncoder.encode("1234"));
        Member member3 = new Member().builder().identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH214").nationality("morocco").accessionDate(LocalDate.now()).build();
        member3.setUsername("g_bahaj"); member3.setFamilyName("bahaj");member3.setName("ghita");member3.setPassword(passwordEncoder.encode("1234"));
        Member member4 = new Member().builder().identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH215").nationality("morocco").accessionDate(LocalDate.now()).build();
        member4.setUsername("o_kharbaq"); member4.setFamilyName("kharbaq");member4.setName("oussama");member4.setPassword(passwordEncoder.encode("1234"));
        Member member5 = new Member().builder().identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH217").nationality("morocco").accessionDate(LocalDate.now()).build();
        member5.setUsername("y_maati"); member5.setFamilyName("maati");member5.setName("youssef");member5.setPassword(passwordEncoder.encode("1234"));
        List<Member> members = List.of(member1, member2, member3, member4, member5);
        return this.memberRepository.saveAll(members);
    }
}
