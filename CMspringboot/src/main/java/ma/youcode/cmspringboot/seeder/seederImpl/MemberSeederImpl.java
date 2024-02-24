package ma.youcode.cmspringboot.seeder.seederImpl;

import lombok.RequiredArgsConstructor;
import ma.youcode.cmspringboot.entity.AppRole;
import ma.youcode.cmspringboot.entity.AppRoleEnum;
import ma.youcode.cmspringboot.entity.IdentityDocumentType;
import ma.youcode.cmspringboot.entity.Member;
import ma.youcode.cmspringboot.repository.MemberRepository;
import ma.youcode.cmspringboot.repository.RoleRepository;
import ma.youcode.cmspringboot.seeder.MemberSeeder;
import ma.youcode.cmspringboot.seeder.RoleSeeder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MemberSeederImpl implements MemberSeeder {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleSeeder roleSeeder;
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public List<Member> createMemberSeeder() {
        Set<AppRole> roles = roleSeeder.getRoles();
        Set<AppRole> roleManager = roles.stream().filter(r -> r.getName().equals(AppRoleEnum.MANAGER)).collect(Collectors.toSet());
        Set<AppRole> roleJury = roles.stream().filter(r -> r.getName().equals(AppRoleEnum.JURY)).collect(Collectors.toSet());
        Set<AppRole> roleMember = roles.stream().filter(r -> r.getName().equals(AppRoleEnum.MEMBER)).collect(Collectors.toSet());

        Member member1 = new Member().builder().identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH212").nationality("morocco").accessionDate(LocalDate.now()).build();
        member1.setUsername("m_moussafia"); member1.setFamilyName("moussafia");member1.setName("mohammed");member1.setPassword(passwordEncoder.encode("1234"));
         member1.setAccountNonLocked(true);member1.setRoles(roleManager);

        Member member2 = new Member().builder().identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH213").nationality("morocco").accessionDate(LocalDate.now()).build();
        member2.setUsername("k_fifel"); member2.setFamilyName("khalid");member2.setName("fifel");member2.setPassword(passwordEncoder.encode("1234"));
        member2.setRoles(roleMember);member2.setAccountNonLocked(true);

        Member member3 = new Member().builder().identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH214").nationality("morocco").accessionDate(LocalDate.now()).build();
        member3.setUsername("g_bahaj"); member3.setFamilyName("bahaj");member3.setName("ghita");member3.setPassword(passwordEncoder.encode("1234"));
        member3.setRoles(roleMember); member3.setAccountNonLocked(true);

        Member member4 = new Member().builder().identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH215").nationality("morocco").accessionDate(LocalDate.now()).build();
        member4.setUsername("o_kharbaq"); member4.setFamilyName("kharbaq");member4.setName("oussama");member4.setPassword(passwordEncoder.encode("1234"));
        member4.setRoles(roleJury); member4.setAccountNonLocked(true);

        Member member5 = new Member().builder().identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH217").nationality("morocco").accessionDate(LocalDate.now()).build();
        member5.setUsername("y_maati"); member5.setFamilyName("maati");member5.setName("youssef");member5.setPassword(passwordEncoder.encode("1234"));
        member5.setRoles(roles); member5.setAccountNonLocked(true);

        List<Member> members = List.of(member1, member2, member3, member4, member5);
        return this.memberRepository.saveAll(members);
    }
}
