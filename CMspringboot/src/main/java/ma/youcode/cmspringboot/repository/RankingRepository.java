package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.entity.Competition;
import ma.youcode.cmspringboot.entity.Member;
import ma.youcode.cmspringboot.entity.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {
    Optional<Ranking> findByCompetitionAndMember(Competition competition, Member member);
    @Query("SELECT r FROM Ranking r " +
            "WHERE r.competition.code = :competition_code AND r.rank IN (1, 2, 3) " +
            "ORDER BY r.rank")
    Optional<List<Ranking>> getPodium(String competition_code);
    @Query("SELECT COUNT(r.competition) FROM Ranking r WHERE r.competition =:competition")
    Integer countAvailablePlace(Competition competition);
    @Query("SELECT Competition FROM Ranking r WHERE r.member =:member")
    Optional<Page<Competition>> findByMember(@Param("member") Member member, Pageable pageable);
}
