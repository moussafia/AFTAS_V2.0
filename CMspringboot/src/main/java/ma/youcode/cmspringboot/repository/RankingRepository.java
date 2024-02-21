package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.model.entity.Competition;
import ma.youcode.cmspringboot.model.entity.Member;
import ma.youcode.cmspringboot.model.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
}
