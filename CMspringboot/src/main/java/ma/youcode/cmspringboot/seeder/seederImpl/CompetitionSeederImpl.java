package ma.youcode.cmspringboot.seeder.seederImpl;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.repository.CompetitionRepository;
import ma.youcode.cmspringboot.seeder.CompetitionSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Component
public class CompetitionSeederImpl implements CompetitionSeeder {
    private CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionSeederImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }
@Override
    public Competition createOne(){
        Competition competition = new Competition().builder().code("hofro12")
                .numberOfParticipants(13)
                .endTime(LocalTime.of(18,0))
                .startDate(LocalTime.of(8,0))
                .date(LocalDate.of(2023,12,18))
                .amount(13F)
                .location("youssoufia").build();
        return this.competitionRepository.save(competition);
    }
    @Override
    public List<Competition> createListCompetition() {
        List<Competition> competitionList = new ArrayList<>();
        competitionList.add(new Competition().builder().amount(300f).code("ims-12-12-23").location("casablanca").date(LocalDate.of(2023, 12, 12)).startDate(LocalTime.of(8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-13-12-23").location("rabat").date(LocalDate.of(2023, 12, 13)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-14-12-23").location("tanger").date(LocalDate.of(2023, 12, 14)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-15-12-23").location("Al Hoceïma").date(LocalDate.of(2023, 12, 15)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-16-12-23").location("agadir").date(LocalDate.of(2023, 12, 16)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-17-12-23").location("sala").date(LocalDate.of(2023, 12, 17)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-18-12-23").location("safi").date(LocalDate.of(2023, 12, 18)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-19-12-23").location("Dakhla").date(LocalDate.of(2023, 12, 19)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-20-12-23").location("casablanca").date(LocalDate.of(2023, 12, 20)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());

        return competitionRepository.saveAll(competitionList);

    }
}
