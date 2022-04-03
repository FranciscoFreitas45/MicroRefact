package DTO;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotBlank;
public class MiscellaneousRecord extends DomainEntity{

 private  String title;

 private  String description;

public MiscellaneousRecord() {
    super();
}
@NotBlank
public String getTitle(){
    return this.title;
}


@NotBlank
public String getDescription(){
    return this.description;
}


}