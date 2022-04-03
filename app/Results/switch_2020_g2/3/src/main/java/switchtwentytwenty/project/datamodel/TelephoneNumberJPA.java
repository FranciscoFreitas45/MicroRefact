package switchtwentytwenty.project.datamodel;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence;
@Entity
@Table(name = "TelephoneNumber")
@NoArgsConstructor
@AllArgsConstructor
public class TelephoneNumberJPA {

@Id
@Getter
 private  String number;

@ManyToOne
@JoinColumn(name = "personID")
@Getter
 private  PersonJPA personJPA;


}