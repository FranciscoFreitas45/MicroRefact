package switchtwentytwenty.project.datamodel;
 import lombok;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@NoArgsConstructor
public class TransferIDJPA implements Serializable{

 private  long serialVersionUID;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  String originAccountID;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  String destinationAccountID;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  String categoryID;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  String description;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  String date;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  String systemDateEntry;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  double balance;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  double amount;


}