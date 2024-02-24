package ma.youcode.cmspringboot.seeder.seederImpl;

import ma.youcode.cmspringboot.entity.Competition;
import ma.youcode.cmspringboot.entity.Member;
import ma.youcode.cmspringboot.entity.Ranking;
import ma.youcode.cmspringboot.repository.RankingRepository;
import ma.youcode.cmspringboot.seeder.RankingSeeder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class RankingSeederImpl implements RankingSeeder {
    private RankingRepository rankingRepository;

    public RankingSeederImpl(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    @Override
    public List<Ranking> createRankingSeeder(List<Member> members, Competition competition) {
        List<Ranking> rankings = new ArrayList<>();
        members.forEach(m->{
              rankings.add(rankingRepository.save(new Ranking().builder().member(m).competition(competition).score((int) (Math.random()*1000)).build()));
        });
        return rankings;
    }
}
