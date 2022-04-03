package switchtwentytwenty.project.DTO;
 import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.AccountID;
import java.util.ArrayList;
import java.util.List;
public class PersonList {

 private  List<Person> list;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

// Constructor Methods
/**
 * Sole constructor.
 */
public PersonList() {
    this.list = new ArrayList<>();
}
public List<Person> getPersonList(){
    return new ArrayList<>(list);
}


public boolean ownsAccount(AccountID accountID){
    for (Person person : list) {
        if (person.isMyAccount(accountID)) {
            return true;
        }
    }
    return false;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ownsAccount"))

.queryParam("accountID",accountID)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}