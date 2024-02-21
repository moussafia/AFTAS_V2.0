package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.model.entity.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Optional<Competition> findByDate(LocalDate date);
    Optional<Competition> findByDateAndCodeLike(LocalDate date, String code);
    Optional<Competition> findByCode(String code);
    @Query("SELECT c from Competition c WHERE c.date <= :date " +
            "OR DATEDIFF(c.date, :date) <= 1")
    Page<Competition> filterCompetitionClosed(LocalDate date, Pageable Pageable);
    @Query("SELECT c from Competition c WHERE c.date > :date " +
            "AND DATEDIFF(c.date, :date) > 1")
    Page<Competition> filterCompetitionPending(LocalDate date, Pageable Pageable);


}
