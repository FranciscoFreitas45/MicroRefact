package switchtwentytwenty.project.datamodel;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence;
@Entity
@Table(name = "Email")
@NoArgsConstructor
@AllArgsConstructor
public class EmailJPA {

@Id
@Getter
 private  String email;

@ManyToOne
@JoinColumn(name = "personID")
 private  PersonJPA personJPA;


}