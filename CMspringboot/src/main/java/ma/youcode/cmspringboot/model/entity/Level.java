package ma.youcode.cmspringboot.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Level {
    @Id
    @Column(unique = true)
    private Integer code;
    private String description;
    @Column(unique = true)
    private Integer points;
    @OneToMany(mappedBy = "level")
    @JsonBackReference
    private Set<Fish> fishSet;
}
