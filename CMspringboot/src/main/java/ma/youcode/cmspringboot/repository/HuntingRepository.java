package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.model.domain.Fish;
import ma.youcode.cmspringboot.model.domain.Hunting;
import ma.youcode.cmspringboot.model.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    Optional<Hunting> findByMemberAndCompetitionAndFish(Member member, Competition competition, Fish fish);
    Optional<List<Hunting>> findByCompetition(Competition competition);
}
