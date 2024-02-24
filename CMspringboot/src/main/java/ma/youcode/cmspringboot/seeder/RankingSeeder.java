package ma.youcode.cmspringboot.seeder;

import ma.youcode.cmspringboot.entity.Competition;
import ma.youcode.cmspringboot.entity.Member;
import ma.youcode.cmspringboot.entity.Ranking;

import java.util.List;

public interface RankingSeeder {
    List<Ranking> createRankingSeeder(List<Member> members, Competition competition);
}
