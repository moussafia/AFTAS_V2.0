package ma.youcode.cmspringboot.seeder.dbSeed;

import ma.youcode.cmspringboot.model.entity.*;
import ma.youcode.cmspringboot.seeder.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbSeed {
    private LevelSeeder levelSeeder;
    private FishSeeder fishSeeder;
    private CompetitionSeeder competitionSeeder;
    private MemberSeeder memberSeeder;
    private RankingSeeder rankingSeeder;

    public DbSeed(LevelSeeder levelSeeder, FishSeeder fishSeeder, CompetitionSeeder competitionSeeder, MemberSeeder memberSeeder, RankingSeeder rankingSeeder) {
        this.levelSeeder = levelSeeder;
        this.fishSeeder = fishSeeder;
        this.competitionSeeder = competitionSeeder;
        this.memberSeeder = memberSeeder;
        this.rankingSeeder = rankingSeeder;
    }

    @Bean
    public CommandLineRunner start(){
        return args -> {
            List<Level> levelList = levelSeeder.saveLevel();
            List<Fish> fishList = fishSeeder.saveFish(levelList);
            List<Competition> competitionList = competitionSeeder.createListCompetition();
            List<Member> members = memberSeeder.createMemberSeeder();
            List<List<Ranking>> rankingList = new ArrayList<>();
            competitionList.forEach(cl->{
                rankingList.add(rankingSeeder.createRankingSeeder(members, cl));
            });
         competitionSeeder.createOne();

        };
    }
}
