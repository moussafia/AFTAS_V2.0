package ma.youcode.cmspringboot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float averageWeight;
    @OneToMany(mappedBy = "fish")
    @JsonBackReference
    private Set<Hunting> huntings;
    @ManyToOne
    private Level level;

}
