package ma.youcode.cmspringboot.service.aftas.serviceImpl;

import lombok.RequiredArgsConstructor;
import ma.youcode.cmspringboot.entity.AppRole;
import ma.youcode.cmspringboot.entity.AppUser;
import ma.youcode.cmspringboot.entity.Member;
import ma.youcode.cmspringboot.repository.MemberRepository;
import ma.youcode.cmspringboot.repository.UserRepository;
import ma.youcode.cmspringboot.service.aftas.MemberService;
import ma.youcode.cmspringboot.service.aftas.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final RoleService roleService;
    private final UserRepository userRepository;


    @Override
    public Member createMember(Member member) {
        ValidateIfExistForCreate(member);
        member.setAccessionDate(LocalDate.now());
        return memberRepository.save(member);
    }
    @Override
    public Member updateMember(Member member) throws NameNotFoundException {
        Member memberExisted = getMemberByNum(member.getNum());
        Set<AppRole> roleSet = ValidateIfRoleExist(member.getRoles());
        member.setRoles(roleSet);
        member.setPassword(memberExisted.getPassword());
        return memberRepository.save(member);
    }

    private Set<AppRole> ValidateIfRoleExist(Set<AppRole> roles) throws NameNotFoundException {
        Set<AppRole> roleSet = new HashSet<>();
        for(AppRole r : roles){
            AppRole role = roleService.findRoleByName(r.getName().name());
            roleSet.add(role);
        }
        return roleSet;
    }

    @Override
    public Member getMemberByNum(Integer num_member) {
        return memberRepository.findByNum(num_member)
                .orElseThrow(() -> new IllegalStateException("Member with number " + num_member + " not found"));
    }
    @Override
    public Member getMemberByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Member with number " + username + " not found"));
    }

    @Override
    public Page<Member> searchMember(String keySearch, Pageable pageable) {
        return memberRepository.searchMember(keySearch, pageable)
                .orElseThrow(() -> new IllegalStateException("Member with name or family name or num " + keySearch + " not found"));
    }

    @Override
    public Page<Member> getAllMember(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    void ValidateIfExistForCreate(Member member){
        memberRepository.findByIdentityNumber(member.getIdentityNumber())
                .ifPresent(m->{
                    throw new IllegalStateException(m.getIdentityNumber() + " is Already exist");
                });
    }

}
