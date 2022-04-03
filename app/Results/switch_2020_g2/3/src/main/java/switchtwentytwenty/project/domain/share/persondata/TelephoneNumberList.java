package switchtwentytwenty.project.domain.share.persondata;
 import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class TelephoneNumberList implements ValueObject{

 private  List<TelephoneNumber> list;

// Constructor Method
/**
 * Sole constructor
 */
public TelephoneNumberList() {
    this.list = new ArrayList<>();
}/**
 * Sole constructor
 */
public TelephoneNumberList(List<String> telephonesNumbers) {
    if (telephonesNumbers == null) {
        this.list = new ArrayList<>();
    } else {
        this.list = new ArrayList<>();
        for (String telephone : telephonesNumbers) {
            list.add(new TelephoneNumber(telephone));
        }
    }
}
public boolean add(TelephoneNumber phoneNumber){
    if (phoneNumber != null) {
        this.list.add(phoneNumber);
        return true;
    }
    return false;
}


public boolean addAll(List<TelephoneNumber> list){
    return this.list.addAll(list);
}


@Override
public int hashCode(){
    return Objects.hash(list);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    TelephoneNumberList that = (TelephoneNumberList) o;
    return list.size() == that.list.size() && list.containsAll(that.list);
}


public List<String> toStringList(){
    List<String> result = new ArrayList<>();
    for (TelephoneNumber telephoneNumber : this.list) {
        result.add(telephoneNumber.toString());
    }
    return result;
}


}