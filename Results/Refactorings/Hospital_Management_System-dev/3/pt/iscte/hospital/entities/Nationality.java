import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Nationality {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long nationalityId;

 private  String name;


public void setName(String name){
    this.name = name;
}


public Long getNationalityId(){
    return nationalityId;
}


public void setNationalityId(Long nationalityId){
    this.nationalityId = nationalityId;
}


public String getName(){
    return name;
}


@Override
public String toString(){
    return "Nationality{" + "nationalityId=" + nationalityId + ", name='" + name + '\'' + '}';
}


}