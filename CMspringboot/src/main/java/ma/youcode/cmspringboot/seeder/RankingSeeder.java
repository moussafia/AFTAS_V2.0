package ma.youcode.cmspringboot.seeder;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.model.domain.Member;
import ma.youcode.cmspringboot.model.domain.Ranking;

import java.util.List;

public interface RankingSeeder {
    List<Ranking> createRankingSeeder(List<Member> members, Competition competition);
}
