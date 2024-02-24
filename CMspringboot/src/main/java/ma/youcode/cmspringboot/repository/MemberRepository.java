package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository  extends JpaRepository<Member, Integer> {
    Optional<Member> findByNumOrNameOrFamilyName(Integer num, String name, String familyName);
    @Query("SELECT m from Member m WHERE m.familyName LIKE %:keyWord% OR m.name LIkE %:keyWord%" +
            "OR CAST(m.num AS string)  LIKE %:keyWord%")
    Optional<Page<Member>> searchMember(String keyWord, Pageable pageable);
    Optional<Member> findByIdentityNumber(String identityNumber);

    Optional<Member> findByNum(Integer integer);
}
