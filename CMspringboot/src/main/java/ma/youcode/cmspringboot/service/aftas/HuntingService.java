package ma.youcode.cmspringboot.service.aftas;

import ma.youcode.cmspringboot.entity.*;

import java.util.List;

public interface HuntingService {
    Hunting insertHuntingForMemberInCompetition(Long fish_id, Integer average_weight,
                                                String competition_code, Integer member_num);
    // Hunting updateHuntingForMemberInCompetition(Hunting hunting);
    public Hunting processHuntingForFish(Member member, Competition competition, Fish fish);
    public void deleteMemberHunting(Hunting hunting);
    Hunting isHuntingExist(Hunting hunting);
   // Optional<Hunting> findByMemberAndFishAndCompetition(Hunting hunting);
    List<Hunting> getAllHuntingOfCompetition(Competition competition);
    Ranking InsertScoreForMemberInCompetition(Ranking ranking, Hunting hunting);

}
