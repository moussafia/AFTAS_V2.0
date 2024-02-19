package ma.youcode.cmspringboot.service;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.model.domain.Member;
import ma.youcode.cmspringboot.model.domain.Ranking;

import java.util.List;
import java.util.Optional;

public interface RankingService {
    public Ranking InscribeMemberToCompetition(Integer num_member, String competition_code);
    public void removeMemberForCompetition(Integer member_num, String competition_code);
    Ranking createScoreForCompetition(Member member, Competition competition);
    Ranking findRankingByCompetitionAndMember(Competition competition, Member member);
    List<Ranking> createRankingForCompetition(String competitionCode);
    List<Ranking> getPodium(String competition_code);
}
