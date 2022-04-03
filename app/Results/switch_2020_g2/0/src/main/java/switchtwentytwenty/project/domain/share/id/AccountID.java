package switchtwentytwenty.project.domain.share.id;
 import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
public class AccountID implements Serializable,ID{

 private  UUID id;

/**
 * Constructor methods
 *
 * @param id - id
 */
public AccountID(UUID id) {
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
    if (!(o instanceof AccountID))
        return false;
    AccountID that = (AccountID) o;
    return Objects.equals(id, that.id);
}


@Override
public String toString(){
    return this.id.toString();
}


}