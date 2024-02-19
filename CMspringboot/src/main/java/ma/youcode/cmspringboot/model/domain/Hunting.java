package ma.youcode.cmspringboot.model.domain;

import javax.persistence.*;

import lombok.*;

@Getter @Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    private Integer numberOfFish;
    @ManyToOne
    private Competition competition;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Fish fish;

}
