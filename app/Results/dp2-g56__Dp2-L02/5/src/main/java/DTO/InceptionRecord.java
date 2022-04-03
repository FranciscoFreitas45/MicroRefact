package DTO;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotBlank;
public class InceptionRecord extends DomainEntity{

 private  String title;

 private  String description;

 private  List<String> photos;

public InceptionRecord() {
    super();
}
@NotBlank
public String getTitle(){
    return this.title;
}


@Valid
@ElementCollection(targetClass = String.class)
public List<String> getPhotos(){
    return this.photos;
}


@NotBlank
public String getDescription(){
    return this.description;
}


}