package DTO;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotBlank;
public class LinkRecord extends DomainEntity{

 private  String title;

 private  String description;

 private  String link;

public LinkRecord() {
    super();
}
@NotBlank
public String getLink(){
    return this.link;
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