package ma.youcode.cmspringboot.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    @Column(name = "identity_number",unique = true)
    private String identityNumber;
    @OneToMany(mappedBy = "member")
    @JsonBackReference
    private Set<Ranking> ranking;
    @OneToMany(mappedBy = "member")
    @JsonBackReference
    private Set<Hunting> huntings;
}
