package ma.youcode.cmspringboot.service;

import ma.youcode.cmspringboot.model.domain.*;

import java.util.List;
import java.util.Optional;

public interface HuntingService {
    Hunting insertHuntingForMemberInCompetition(Long fish_id, Integer average_weight,
                                                String competition_code, Integer member_num);
    // Hunting updateHuntingForMemberInCompetition(Hunting hunting);
    public Hunting processHuntingForFish(Member member, Competition competition, Fish fish);
    public void deleteMemberHunting(Hunting hunting);
    Hunting isHuntingExist(Hunting hunting);
   // Optional<Hunting> findByMemberAndFishAndCompetition(Hunting hunting);
    List<Hunting> getAllHuntingOfCompetition(Competition competition);
    Ranking InsertScoreForMemberInCompetition(Ranking ranking,Hunting hunting);

}
