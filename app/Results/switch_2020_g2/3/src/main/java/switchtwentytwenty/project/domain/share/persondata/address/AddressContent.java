package switchtwentytwenty.project.domain.share.persondata.address;
 import java.util.Objects;
public class AddressContent {

 final  String denomination;

// Constructor Methods
/**
 * Sole constructor.
 *
 * @param denomination denomination
 */
protected AddressContent(String denomination) {
    Objects.requireNonNull(denomination);
    this.denomination = denomination;
}
@Override
public String toString(){
    return denomination;
}


}