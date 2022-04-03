package switchtwentytwenty.project.dto.todomaindto;
 import lombok.Getter;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class LedgerJpaToDomainDTO {

@Getter
 private  LedgerID id;

@Getter
 private  List<Transaction> transactionList;

public LedgerJpaToDomainDTO(LedgerID id) {
    this.id = id;
    this.transactionList = new ArrayList<>();
}
@Override
public int hashCode(){
    return Objects.hash(id);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof LedgerJpaToDomainDTO))
        return false;
    LedgerJpaToDomainDTO that = (LedgerJpaToDomainDTO) o;
    return Objects.equals(id, that.id);
}


}