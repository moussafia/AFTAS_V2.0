package ma.youcode.cmspringboot.service.serviceImpl;

import ma.youcode.cmspringboot.model.domain.*;
import ma.youcode.cmspringboot.repository.HuntingRepository;
import ma.youcode.cmspringboot.service.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
@Service
public class HuntingServiceImpl implements HuntingService {
    private HuntingRepository huntingRepository;
    private FishService fishService;
    private CompetitionService competitionService;
    private MemberService memberService;
    private RankingService rankingService;

    public HuntingServiceImpl(HuntingRepository huntingRepository,
                              FishService fishService,
                              CompetitionService competitionService,
                              MemberService memberService,
                              RankingService rankingService) {
        this.huntingRepository = huntingRepository;
        this.fishService = fishService;
        this.competitionService = competitionService;
        this.memberService = memberService;
        this.rankingService = rankingService;
    }

    @Override
    public Hunting insertHuntingForMemberInCompetition(Long fish_id, Integer average_weight,
                                                       String competition_code, Integer member_num) {
        Competition competition = competitionService.getCompetitionByCode(competition_code);
        Fish fish = fishService.getFishById(fish_id);
        Member member = memberService.getMemberByNum(member_num);
        Ranking ranking = rankingService.findRankingByCompetitionAndMember(competition, member);
        validateDateCreateHunting(competition);
        processCheckWeightOfFish(average_weight,fish);
        Hunting huntingForSaving = processHuntingForFish(member, competition, fish);
        Hunting hunting = huntingRepository.save(huntingForSaving);
        InsertScoreForMemberInCompetition(ranking, hunting);
        return hunting;
    }

    @Override
    public Ranking InsertScoreForMemberInCompetition(Ranking ranking, Hunting hunting) {
        int score = hunting.getFish().getLevel().getPoints();
        ranking.setScore(ranking.getScore() + score);
        return rankingService.createScoreForCompetition(ranking.getMember(), ranking.getCompetition());
    }

//    @Override
//    public Hunting updateHuntingForMemberInCompetition(Hunting hunting) {
//        Hunting huntingForUpdate = processUpdateHuntingForFish(hunting);
//        return huntingRepository.save(huntingForUpdate);
//    }

    private void processCheckWeightOfFish(Integer average_weight,Fish fish) {
        if(average_weight < fish.getAverageWeight())
            throw new IllegalStateException("Average weight " + average_weight + " for the fish named '"
                    + fish.getName() + "'  should be equals or superior than "
            + fish.getAverageWeight());
    }
    @Override
    public Hunting processHuntingForFish(Member member,Competition competition,Fish fish) {
        Optional<Hunting> huntingSaved = huntingRepository
                .findByMemberAndCompetitionAndFish(member, competition, fish);
        Hunting huntingForSaving;
        if (huntingSaved.isPresent()){
            huntingForSaving = new Hunting().builder()
                    .id(huntingSaved.get().getId())
                    .competition(huntingSaved.get().getCompetition())
                    .member(huntingSaved.get().getMember())
                    .fish(huntingSaved.get().getFish())
                    .numberOfFish(huntingSaved.get().getNumberOfFish() + 1)
                    .build();
        }
        else{
            huntingForSaving = new Hunting().builder()
                    .competition(competition)
                    .member(member)
                    .fish(fish)
                    .numberOfFish(1)
                    .build();
        }
        return huntingForSaving;
    }
//    @Override
//    public Hunting processUpdateHuntingForFish(Member member,Competition competition,Fish fish){
//        Optional<Hunting> huntingSaved = huntingRepository.findByMemberAndCompetitionAndFish(member, competition, fish);
//        return new Hunting().builder()
//                .id(huntingSaved.get().getId())
//                .competition(huntingSaved.get().getCompetition())
//                .member(huntingSaved.get().getMember())
//                .fish(huntingSaved.get().getFish())
//                .numberOfFish(hunting.getNumberOfFish())
//                .build();
//    }

    @Override
    public void deleteMemberHunting(Hunting hunting) {
        Hunting huntingForDelete = isHuntingExist(hunting);
        huntingRepository.delete(huntingForDelete);
    }
    @Override
    public Hunting isHuntingExist(Hunting hunting){
        return huntingRepository.findByMemberAndCompetitionAndFish(hunting.getMember(), hunting.getCompetition(), hunting.getFish())
                .orElseThrow(() -> new IllegalStateException("Hunting entry not found for Member: "
                        + hunting.getMember().getNum() + ", Competition: " + hunting.getCompetition().getCode()
                        + ", Fish ID: " + hunting.getFish().getId()));
    }

    @Override
    public List<Hunting> getAllHuntingOfCompetition(Competition competition) {
        return huntingRepository.findByCompetition(competition)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No hunting entries found for competition with code: " + competition.getCode()));
    }
    public void validateDateCreateHunting(Competition competition){
        if(LocalDate.now().isAfter(competition.getDate()) || LocalDate.now().isBefore(competition.getDate()))
            throw new IllegalStateException("you cannot insert hunting before or after date competition");
        if(LocalTime.now().isBefore(competition.getStartDate()) || LocalTime.now().isAfter(competition.getEndTime()))
            throw new IllegalStateException("you cannot insert list hunting before start time of competition or after");
    }

}
