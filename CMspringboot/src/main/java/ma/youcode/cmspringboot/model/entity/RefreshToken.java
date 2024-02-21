package ma.youcode.cmspringboot.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String refreshToken;
    @Column(nullable = false)
    private Instant expiryDate;
    private boolean revoked;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;
}
