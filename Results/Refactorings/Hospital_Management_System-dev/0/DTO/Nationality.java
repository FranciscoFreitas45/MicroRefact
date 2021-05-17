import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
public class Nationality {

 private  Long nationalityId;

 private  String name;


public Long getNationalityId(){
    return nationalityId;
}


public String getName(){
    return name;
}


}