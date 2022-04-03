package switchtwentytwenty.project.domain.share.transactiondata;
 import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;
import java.util.Objects;
public class MovementType {

 private  String type;

// Constructor Methods
/**
 * Sole Constructor
 *
 * @param type - type
 * @throws InvalidMovementTypeException
 */
public MovementType(String type) throws InvalidMovementTypeException {
    if (type == null) {
        throw new NullPointerException("Parameter is null");
    }
    type = type.toLowerCase();
    if (Constants.MOVEMENTS_TYPE.contains(type)) {
        this.type = type;
    } else {
        throw new InvalidMovementTypeException("Invalid Type");
    }
}
@Override
public int hashCode(){
    return Objects.hash(type);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof MovementType))
        return false;
    MovementType that = (MovementType) o;
    return Objects.equals(type, that.type);
}


@Override
public String toString(){
    return this.type;
}


}