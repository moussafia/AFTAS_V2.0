package ma.youcode.cmspringboot.service.aftas;

import ma.youcode.cmspringboot.model.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {
    Member createMember(Member member);
    public Member updateMember(Member member);
    public Member getMemberByNum(Integer member_Num);
    public Page<Member> searchMember(String keySearch, Pageable pageable);
    Page<Member> getAllMember(Pageable pageable);
}
