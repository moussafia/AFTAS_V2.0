package ma.youcode.cmspringboot.service.aftas;

import ma.youcode.cmspringboot.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.naming.NameNotFoundException;

public interface MemberService {
    Member createMember(Member member);
    public Member updateMember(Member member) throws NameNotFoundException;
    public Member getMemberByNum(Integer member_Num);
    public Member getMemberByUsername(String username);
    public Page<Member> searchMember(String keySearch, Pageable pageable);
    Page<Member> getAllMember(Pageable pageable);
}
