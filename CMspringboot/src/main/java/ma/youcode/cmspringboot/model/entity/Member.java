package ma.youcode.cmspringboot.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
