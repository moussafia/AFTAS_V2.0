package ma.youcode.cmspringboot.seeder.seederImpl;

import ma.youcode.cmspringboot.model.entity.Competition;
import ma.youcode.cmspringboot.repository.CompetitionRepository;
import ma.youcode.cmspringboot.seeder.CompetitionSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
        Competition competition = new Competition().builder().code("hofro03")
                .numberOfParticipants(13)
                .endTime(LocalTime.of(18,0))
                .startDate(LocalTime.of(8,0))
                .date(LocalDate.of(2024,03,18))
                .amount(13F)
                .location("youssoufia").build();
        return this.competitionRepository.save(competition);
    }
    @Override
    public List<Competition> createListCompetition() {
        List<Competition> competitionList = new ArrayList<>();
        competitionList.add(new Competition().builder().amount(300f).code("ims-21-02-24").location("casablanca").date(LocalDate.of(2024, 02, 21)).startDate(LocalTime.of(8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-13-03-24").location("rabat").date(LocalDate.of(2024, 03, 13)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-14-03-24").location("tanger").date(LocalDate.of(2024, 03, 14)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-15-03-24").location("Al Hoceïma").date(LocalDate.of(2024, 03, 15)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-16-03-24").location("agadir").date(LocalDate.of(2024, 03, 16)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-17-03-24").location("sala").date(LocalDate.of(2024, 03, 17)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-18-03-24").location("safi").date(LocalDate.of(2024, 03, 18)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-19-03-24").location("Dakhla").date(LocalDate.of(2024, 03, 19)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-20-03-24").location("casablanca").date(LocalDate.of(2024, 03, 20)).startDate(LocalTime.of( 8, 0)).endTime(LocalTime.of(20, 0)).numberOfParticipants((int) (Math.random()*400)).build());

        return competitionRepository.saveAll(competitionList);

    }
}
