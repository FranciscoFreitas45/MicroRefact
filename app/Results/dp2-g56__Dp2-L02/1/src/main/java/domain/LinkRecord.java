package domain;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotBlank;
@Entity
@Access(AccessType.PROPERTY)
public class LinkRecord extends DomainEntity{

 private  String title;

 private  String description;

 private  String link;

public LinkRecord() {
    super();
}
public void setLink(String link){
    this.link = link;
}


@NotBlank
public String getLink(){
    return this.link;
}


@NotBlank
public String getTitle(){
    return this.title;
}


public void setTitle(String title){
    this.title = title;
}


public void setDescription(String description){
    this.description = description;
}


@NotBlank
public String getDescription(){
    return this.description;
}


}