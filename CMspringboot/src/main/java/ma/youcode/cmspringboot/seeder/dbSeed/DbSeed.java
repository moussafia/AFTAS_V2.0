package ma.youcode.cmspringboot.seeder.dbSeed;

import lombok.RequiredArgsConstructor;
import ma.youcode.cmspringboot.entity.*;
import ma.youcode.cmspringboot.seeder.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DbSeed {
    private final LevelSeeder levelSeeder;
    private final FishSeeder fishSeeder;
    private final CompetitionSeeder competitionSeeder;
    private final MemberSeeder memberSeeder;
    private final RankingSeeder rankingSeeder;
    private final RoleSeeder roleSeeder;



    @Bean
    public CommandLineRunner start(){
        return args -> {
            roleSeeder.createRoles();
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
