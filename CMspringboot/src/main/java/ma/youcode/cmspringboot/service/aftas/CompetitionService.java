package ma.youcode.cmspringboot.service.aftas;

import ma.youcode.cmspringboot.model.entity.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompetitionService {
    public Competition createCompetition(Competition competition);
    public Competition getCompetitionByCode(String competition);
    public Competition updateCompetition(Competition competition);
    public Page<Competition> getAllCompetition(Pageable pageable);
    public Page<Competition> filterCompetitionByDate(boolean isClosed,Pageable pageable);
    public Competition getCompetitionByDate();

}
