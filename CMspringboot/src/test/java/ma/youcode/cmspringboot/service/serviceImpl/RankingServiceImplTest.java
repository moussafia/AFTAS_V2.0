package ma.youcode.cmspringboot.service.serviceImpl;

import liquibase.pro.packaged.M;
import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.model.domain.IdentityDocumentType;
import ma.youcode.cmspringboot.model.domain.Member;
import ma.youcode.cmspringboot.model.domain.Ranking;
import ma.youcode.cmspringboot.repository.CompetitionRepository;
import ma.youcode.cmspringboot.repository.RankingRepository;
import ma.youcode.cmspringboot.service.CompetitionService;
import ma.youcode.cmspringboot.service.MemberService;
import ma.youcode.cmspringboot.service.RankingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RankingServiceImplTest {
    @Mock
    private RankingServiceImpl rankingService;

    @Mock
    private RankingRepository rankingRepository;
    @Mock
    private MemberService memberService;
    @Mock
    private CompetitionService competitionService;
    @Mock
    private CompetitionRepository competitionRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRankingForCompetition() {
        String competitionCode = "yo-23-12-10";
        Competition competitionTest1 = createFakeScore(1,2,3);
        when(competitionService.getCompetitionByCode(competitionCode))
                .thenReturn(competitionTest1);
        List<Ranking> rankings = competitionTest1.getRanking().stream().toList();
        when(rankingService.createRankingForCompetition(competitionCode))
                .thenReturn(rankings);
        assertEquals(rankingService.createRankingForCompetition(competitionCode),rankings);

    }
    public Competition createFakeScore(Integer ScoreMemberOne, Integer scoreMemberTwo, Integer scoreMemberThree){
        Competition competition = createFakeDataCompetition();
        List<Ranking> ranking = createFakeDataRanking();
        ranking.get(0).setScore(ScoreMemberOne);
        ranking.get(1).setScore(scoreMemberTwo);
        ranking.get(2).setScore(scoreMemberThree);
        competition.setRanking(ranking.stream().collect(Collectors.toSet()));
        return competition;
    }
    public Competition createFakeDataCompetition(){
        Competition competition = new Competition().builder()
                .location("youssoufia")
                .endTime(LocalTime.of(20,10,3))
                .startDate(LocalTime.of(8,10,3))
                .date(LocalDate.of(2023,12,18))
                .amount(30F)
                .code("yo-23-12-10")
                .numberOfParticipants(23)
                .build();
        return competition;
    }
    public List<Ranking> createFakeDataRanking(){
        Competition competition = createFakeDataCompetition();
        List<Ranking> rankings = new ArrayList<>();
        createFakeDataMember().forEach(m->{
            rankings.add(new Ranking().builder().member(m).competition(competition).build());
        });
        return rankings;
    }
    public List<Member> createFakeDataMember(){
        return List.of(
                new Member().builder().name("mohammed").familyName("moussafia").identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH212").accessionDate(LocalDate.now()).build(),
                new Member().builder().name("saad").familyName("moumou").identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH213").accessionDate(LocalDate.now()).build(),
                new Member().builder().name("khalid").familyName("fifel").identityDocumentType(IdentityDocumentType.CIN).identityNumber("HH214").accessionDate(LocalDate.now()).build()
        );
    }

//    @Override
//    public List<Ranking> createRankingForCompetition(String competitionCode) {
//        Competition competition = competitionService.getCompetitionByCode(competitionCode);
//        Set<Ranking> rankings = competition.getRanking();
//        final Integer[] index = {0};
//        final Integer[] score= {0};
//        return rankingRepository.saveAll(rankings.stream()
//                .sorted(Comparator.comparing(Ranking::getScore).reversed())
//                .map(hunting -> {
//                    if(hunting.getScore() != 0 && hunting.getScore() != score[0]){
//                        index[0]++;
//                        hunting.setRank(index[0]);
//                        score[0]=hunting.getScore();
//                    }else if(hunting.getScore() == score[0]){
//                        if(index[0] != 0) hunting.setRank(index[0]);
//                        else hunting.setRank(competition.getNumberOfParticipants());
//                    }else{
//                        hunting.setRank(competition.getNumberOfParticipants());
//                    }
//                    return hunting;
//                }).collect(Collectors.toList()));
//    }
}