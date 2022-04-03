package switchtwentytwenty.project.domain.share.list;
 import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.AccountID;
import java.util.ArrayList;
import java.util.List;
public class PersonList {

 private  List<Person> list;

// Constructor Methods
/**
 * Sole constructor.
 */
public PersonList() {
    this.list = new ArrayList<>();
}
public void add(Person person){
    this.list.add(person);
}


public int size(){
    return list.size();
}


public boolean ownsAccount(AccountID accountID){
    for (Person person : list) {
        if (person.isMyAccount(accountID)) {
            return true;
        }
    }
    return false;
}


public List<Person> getPersonList(){
    return new ArrayList<>(list);
}


}