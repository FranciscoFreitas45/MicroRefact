package switchtwentytwenty.project.domain.share.persondata.address;
 import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import java.util.Objects;
public class City extends AddressContentimplements ValueObject{

// Constructor methods
/**
 * Sole constructor.
 *
 * @param denomination string
 */
public City(String denomination) {
    super(denomination);
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
    City otherCity = (City) o;
    return Objects.equals(denomination, otherCity.denomination);
}


}