package ma.youcode.cmspringboot.entity;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    @Id @NotNull
    private String code;
    @NotNull(message = "date shouldn't be null")
    private LocalDate date;
    @NotNull(message = "star date shouldn't be null")
    private LocalTime startDate;
    @NotNull(message = "end date shouldn't be null")
    private LocalTime endTime;
    @NotNull(message = "number of participants shouldn't be null")
    private int numberOfParticipants;
    @Size(min = 3, max = 200, message = "length is too low or too height")
    @NotNull(message = "location shouldn't be null")
    private String location;
    @NotNull(message = "amount shouldn't be null")
    private Float amount;
    @OneToMany(mappedBy = "competition")
    private Set<Ranking> ranking;
    @OneToMany(mappedBy = "competition")
    private Set<Hunting> hunting;
}
