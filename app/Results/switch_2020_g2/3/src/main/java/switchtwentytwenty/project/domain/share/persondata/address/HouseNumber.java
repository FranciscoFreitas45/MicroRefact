package switchtwentytwenty.project.domain.share.persondata.address;
 import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import java.util.Objects;
public class HouseNumber extends AddressContentimplements ValueObject{

// Constructor methods
/**
 * Sole constructor.
 *
 * @param number string
 */
public HouseNumber(String number) {
    super(number);
}
@Override
public int hashCode(){
    return Objects.hash(denomination);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    HouseNumber that = (HouseNumber) o;
    return Objects.equals(denomination, that.denomination);
}


}