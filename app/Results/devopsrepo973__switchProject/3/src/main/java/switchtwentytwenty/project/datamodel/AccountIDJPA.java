package switchtwentytwenty.project.datamodel;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence;
@Entity
@Table(name = "AccountID")
@NoArgsConstructor
@AllArgsConstructor
public class AccountIDJPA {

@Id
@Getter
 private  String id;

@ManyToOne
@JoinColumn(name = "personID")
@Getter
 private  PersonJPA personJPA;


public boolean hasSameID(String otherID){
    return id.equals(otherID);
}


}