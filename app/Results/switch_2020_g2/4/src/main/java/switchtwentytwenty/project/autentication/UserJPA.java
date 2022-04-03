package switchtwentytwenty.project.autentication;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "email"), @UniqueConstraint(columnNames = "username") })
@NoArgsConstructor
public class UserJPA {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Getter
 private  Long id;

@NotBlank
@Size(max = 50)
@Email
@Getter
@Setter
 private  String email;

@NotBlank
@Size(max = 20)
@Getter
@Setter
 private  String username;

@NotBlank
@Size(max = 120)
@Setter
@Getter
 private  String password;

@NotBlank
@Size(max = 120)
@Getter
@Setter
 private  String familyID;

@Getter
@Setter
@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
 private  Set<RoleJPA> roles;

public UserJPA(String username, String email, String password, String familyID) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.familyID = familyID;
}
}