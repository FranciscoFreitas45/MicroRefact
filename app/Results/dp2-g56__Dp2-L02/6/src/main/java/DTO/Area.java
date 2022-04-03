package DTO;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotBlank;
public class Area extends DomainEntity{

 private  String name;

 private  List<String> pictures;


@NotBlank
public String getName(){
    return this.name;
}


@Valid
@ElementCollection(targetClass = String.class)
public List<String> getPictures(){
    return this.pictures;
}


}