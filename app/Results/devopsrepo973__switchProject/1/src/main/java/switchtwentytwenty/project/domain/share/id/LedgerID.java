package switchtwentytwenty.project.domain.share.id;
 import java.util.Objects;
import java.util.UUID;
public class LedgerID implements ID{

 private  UUID id;

// Constructor Methods
/**
 * Sole constructor.
 *
 * @param id - identifier
 */
public LedgerID(UUID id) {
    Objects.requireNonNull(id);
    this.id = id;
}
@Override
public int hashCode(){
    return Objects.hash(id);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    LedgerID ledgerID = (LedgerID) o;
    return Objects.equals(id, ledgerID.id);
}


@Override
public String toString(){
    return this.id.toString();
}


}