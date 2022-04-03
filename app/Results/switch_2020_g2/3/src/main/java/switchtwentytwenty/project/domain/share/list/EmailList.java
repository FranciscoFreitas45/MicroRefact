package switchtwentytwenty.project.domain.share.list;
 import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.domain.share.id.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class EmailList implements ValueObject{

 private  List<Email> list;

// Constructor Methods
/**
 * Sole Constructor
 */
public EmailList() {
    this.list = new ArrayList<>();
}
public boolean add(Email email){
    if (email != null) {
        this.list.add(email);
        return true;
    }
    return false;
}


public boolean contains(Email inEmail){
    for (Email email : this.list) {
        if (email.equals(inEmail)) {
            return true;
        }
    }
    return false;
}


public boolean addAll(List<Email> list){
    if (list != null && !list.isEmpty()) {
        this.list.addAll(list);
        return true;
    }
    return false;
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
    EmailList that = (EmailList) o;
    return list.size() == that.list.size() && list.containsAll(that.list);
}


public List<Email> getEmailList(){
    List<Email> other = new ArrayList<>();
    for (Email email : list) {
        other.add(email);
    }
    return other;
}


public List<String> toStringList(){
    List<String> result = new ArrayList<>();
    for (Email email : this.list) {
        result.add(email.toString());
    }
    return result;
}


public boolean remove(Email email){
    if (email != null) {
        list.remove(email);
        return true;
    }
    return false;
}


}