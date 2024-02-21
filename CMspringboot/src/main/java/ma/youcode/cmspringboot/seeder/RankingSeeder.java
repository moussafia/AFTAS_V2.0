package ma.youcode.cmspringboot.seeder;

import ma.youcode.cmspringboot.model.entity.Competition;
import ma.youcode.cmspringboot.model.entity.Member;
import ma.youcode.cmspringboot.model.entity.Ranking;

import java.util.List;

public interface RankingSeeder {
    List<Ranking> createRankingSeeder(List<Member> members, Competition competition);
}
