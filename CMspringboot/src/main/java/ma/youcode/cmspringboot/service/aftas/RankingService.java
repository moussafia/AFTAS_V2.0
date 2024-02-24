package ma.youcode.cmspringboot.service.aftas;

import ma.youcode.cmspringboot.entity.Competition;
import ma.youcode.cmspringboot.entity.Member;
import ma.youcode.cmspringboot.entity.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RankingService {
    public Ranking InscribeMemberToCompetition(Integer num_member, String competition_code);
    public void removeMemberForCompetition(Integer member_num, String competition_code);
    Ranking createScoreForCompetition(Member member, Competition competition);
    Ranking findRankingByCompetitionAndMember(Competition competition, Member member);
    List<Ranking> createRankingForCompetition(String competitionCode);
    List<Ranking> getPodium(String competition_code);
    Page<Competition> getMyCompetition(Pageable pageable);
}
