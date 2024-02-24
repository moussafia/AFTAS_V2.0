package ma.youcode.cmspringboot.service.aftas.serviceImpl;

import ma.youcode.cmspringboot.entity.Member;
import ma.youcode.cmspringboot.repository.MemberRepository;
import ma.youcode.cmspringboot.service.aftas.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    @Override
    public Member createMember(Member member) {
        ValidateIfExistForCreate(member);
        member.setAccessionDate(LocalDate.now());
        return memberRepository.save(member);
    }
    @Override
    public Member updateMember(Member member) {
        ValidateIfExistForUpdate(member);
        return memberRepository.save(member);
    }

    @Override
    public Member getMemberByNum(Integer num_member) {
        return memberRepository.findByNum(num_member)
                .orElseThrow(() -> new IllegalStateException("Member with number " + num_member + " not found"));
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
    void ValidateIfExistForUpdate(Member member){
        Member memberExisted = memberRepository.findByNum(member.getNum()).orElse(null);
        if (memberExisted == null)
            throw new IllegalStateException("member with number adherent " + member.getNum() + " not exist");
    }

}
