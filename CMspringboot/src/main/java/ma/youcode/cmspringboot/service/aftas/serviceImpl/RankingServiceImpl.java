package ma.youcode.cmspringboot.service.aftas.serviceImpl;

import ma.youcode.cmspringboot.model.entity.Competition;
import ma.youcode.cmspringboot.model.entity.Member;
import ma.youcode.cmspringboot.model.entity.Ranking;
import ma.youcode.cmspringboot.repository.CompetitionRepository;
import ma.youcode.cmspringboot.repository.RankingRepository;
import ma.youcode.cmspringboot.service.aftas.CompetitionService;
import ma.youcode.cmspringboot.service.aftas.MemberService;
import ma.youcode.cmspringboot.service.aftas.RankingService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class RankingServiceImpl implements RankingService {
    private RankingRepository rankingRepository;
    private MemberService memberService;
    private CompetitionService competitionService;
    private final CompetitionRepository competitionRepository;

    public RankingServiceImpl(RankingRepository rankingRepository, MemberService memberService, CompetitionService competitionService,
                              CompetitionRepository competitionRepository) {
        this.rankingRepository = rankingRepository;
        this.memberService = memberService;
        this.competitionService = competitionService;
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Ranking InscribeMemberToCompetition(Integer num_member, String competition_code) {
        Member member = validateMemberIfExist(num_member);
        Competition competition = validateCompetitionIfExist(competition_code);
        validateIfMemberAlreadyInscribeInCompetition(member, competition);
        validateAvailabilityDateForInscribe(competition);
        validateAvailablePlace(competition);
        Ranking ranking = new Ranking().builder()
                .member(member)
                .competition(competition).build();
        return rankingRepository.save(ranking);
    }

    private void validateAvailablePlace(Competition competition) {
        Integer numberOfPlace = competition.getNumberOfParticipants();
        Integer countAvailablePlace = rankingRepository.countAvailablePlace(competition);
        if(numberOfPlace < countAvailablePlace)
            throw new IllegalStateException("number of place is not available");
    }

    @Override
    public void removeMemberForCompetition(Integer member_num, String competition_code) {
        Member member = validateMemberIfExist(member_num);
        Competition competition = validateCompetitionIfExist(competition_code);
        Ranking rankingForSaving = findRankingByCompetitionAndMember(competition, member);
        rankingRepository.delete(rankingForSaving);
    }

    @Override
    public Ranking createScoreForCompetition(Member member, Competition competition) {
        Ranking ranking = rankingRepository.findByCompetitionAndMember(competition, member)
                .orElseThrow(() -> new  IllegalStateException("member with name " + member.getName() + " is not inscribe " +
                        "in this competition " + competition.getCode()));
        return rankingRepository.save(ranking);
    }

    @Override
    public Ranking findRankingByCompetitionAndMember(Competition competition, Member member) {
        return  rankingRepository.findByCompetitionAndMember(competition, member)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Member with name '" + member.getName() +
                                "' not inscribe in  '" + competition.getCode() + "'"));
    }

    @Override
    public List<Ranking> createRankingForCompetition(String competitionCode) {
        Competition competition = competitionService.getCompetitionByCode(competitionCode);
        Set<Ranking> rankings = competition.getRanking();
        final Integer[] index = {0};
        final Integer[] score= {0};
        List<Ranking> result = rankings.stream()
                .sorted(Comparator.comparing(Ranking::getScore).reversed())
                .map(hunting -> {
                    if (hunting.getScore() != 0 && hunting.getScore() != score[0]) {
                        index[0]++;
                        hunting.setRank(index[0]);
                        score[0] = hunting.getScore();
                    } else if (hunting.getScore() == score[0]) {
                        if (index[0] != 0) hunting.setRank(index[0]);
                        else hunting.setRank(competition.getNumberOfParticipants());
                    } else {
                        hunting.setRank(competition.getNumberOfParticipants());
                    }
                    return hunting;
                }).collect(Collectors.toList());
        return rankingRepository.saveAll(result);
    }

    @Override
    public List<Ranking> getPodium(String competition_code) {
        return rankingRepository.getPodium(competition_code)
                .orElseThrow(()-> new IllegalStateException("no podium for this competition with code " + competition_code));
    }

    private Member validateMemberIfExist(Integer member_num) {

        return memberService.getMemberByNum(member_num);
    }

    private Competition validateCompetitionIfExist(String competition_code) {
        return competitionService.getCompetitionByCode(competition_code);
    }


    void validateIfMemberAlreadyInscribeInCompetition(Member member, Competition competition){
        Optional<Ranking> rankingExisted = rankingRepository.findByCompetitionAndMember(competition, member);
        if(rankingExisted.isPresent())
            throw new IllegalStateException("member with name " + member.getName()
            + " already inscribe in this competition with name " + competition.getCode());

    }
    void validateAvailabilityDateForInscribe(Competition competition) {
        long hoursUntilCompetition = ChronoUnit.DAYS.between(LocalDate.now(), competition.getDate());
        if(hoursUntilCompetition < 0)
            throw new IllegalStateException("Cannot inscribe for this competition with a date '" +
                    competition.getDate() + ", already played");
        if(hoursUntilCompetition >= 0 && hoursUntilCompetition <= 1){
            throw new IllegalStateException("Cannot inscribe for this competition with a date '" +
                    competition.getDate() + "' less than 24 hours from now. Remaining time: " + hoursUntilCompetition + " day.");
        }
    }
}
