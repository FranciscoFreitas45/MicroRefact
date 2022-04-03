package switchtwentytwenty.project.dto.outdto;
 import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
public class TransferOutDTO extends RepresentationModel<TransferOutDTO>{

@Getter
 private  String originAccountID;

@Getter
 private  String destinationAccountID;

@Getter
 private  String date;

@Getter
 private  double amount;

// Constructor Methods
public TransferOutDTO(String originAccountID, String destinationAccountID, String date, double amount) {
    Objects.requireNonNull(originAccountID);
    Objects.requireNonNull(destinationAccountID);
    Objects.requireNonNull(date);
    this.originAccountID = originAccountID;
    this.destinationAccountID = destinationAccountID;
    this.date = date;
    this.amount = amount;
}
@Override
public int hashCode(){
    return Objects.hash(super.hashCode(), originAccountID, destinationAccountID, date, amount);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof TransferOutDTO))
        return false;
    TransferOutDTO that = (TransferOutDTO) o;
    return Double.compare(that.amount, amount) == 0 && Objects.equals(originAccountID, that.originAccountID) && Objects.equals(destinationAccountID, that.destinationAccountID) && Objects.equals(date, that.date);
}


}