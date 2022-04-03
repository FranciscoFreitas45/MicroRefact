package pl.szymanski.sharelibrary.entity;
 import lombok.Data;
import javax.persistence;
import java.util.Arrays;
import java.util.List;
import javax.persistence.CascadeType;
import pl.szymanski.sharelibrary.Request.CoordinatesRequest;
import pl.szymanski.sharelibrary.Request.Impl.CoordinatesRequestImpl;
import pl.szymanski.sharelibrary.DTO.Coordinates;
@Entity
@Data
@Table(name = "users")
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(nullable = false, unique = true)
 private  String email;

@Column(nullable = false)
 private  char[] password;

@Column(nullable = false, unique = true)
 private  String username;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String name;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String surname;

@OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
 private  List<UserBook> books;

@Transient
 private  Coordinates coordinates;

@Column(name = "id")
 private Long id;

@Transient
 private CoordinatesRequest coordinatesrequest = new CoordinatesRequestImpl();;


@Override
public String toString(){
    return "User{" + "id=" + id + ", email='" + email + '\'' + ", password=" + Arrays.toString(password) + ", username='" + username + '\'' + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", coordinates=" + coordinates + '}';
}


}