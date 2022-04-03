package switchtwentytwenty.project.autentication;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence;
import java.util.Objects;
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class RoleJPA {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Setter
 private  Integer id;

@Enumerated(EnumType.STRING)
@Column(length = 20)
@Getter
 private  ERole name;

public RoleJPA(ERole name) {
    this.name = name;
}
@Override
public int hashCode(){
    return Objects.hash(id, name);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    RoleJPA roleJPA = (RoleJPA) o;
    return id.equals(roleJPA.id) && name == roleJPA.name;
}


}