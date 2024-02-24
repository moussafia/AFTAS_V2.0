package ma.youcode.cmspringboot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
public class Member extends AppUser {
    private String nationality;
    private LocalDate accessionDate;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    @Column(name = "identity_number", unique = true)
    private String identityNumber;
    @OneToMany(mappedBy = "member")
    @JsonBackReference
    private Set<Ranking> ranking;
    @OneToMany(mappedBy = "member")
    @JsonBackReference
    private Set<Hunting> huntings;

}
