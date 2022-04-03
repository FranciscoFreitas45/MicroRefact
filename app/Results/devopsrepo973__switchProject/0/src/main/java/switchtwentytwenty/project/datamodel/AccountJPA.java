package switchtwentytwenty.project.datamodel;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Account")
@NoArgsConstructor
@AllArgsConstructor
public class AccountJPA {

@Id
@Getter
 private  String id;

@Getter
 private  String balance;

@Getter
 private  String designation;

@Getter
 private  String type;


}