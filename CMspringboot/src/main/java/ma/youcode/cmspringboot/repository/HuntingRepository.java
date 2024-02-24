package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.entity.Competition;
import ma.youcode.cmspringboot.entity.Fish;
import ma.youcode.cmspringboot.entity.Hunting;
import ma.youcode.cmspringboot.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    Optional<Hunting> findByMemberAndCompetitionAndFish(Member member, Competition competition, Fish fish);
    Optional<List<Hunting>> findByCompetition(Competition competition);
}
