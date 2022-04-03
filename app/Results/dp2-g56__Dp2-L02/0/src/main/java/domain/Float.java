package domain;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotBlank;
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "`Float`")
public class Float extends DomainEntity{

 private  String title;

 private  String description;

 private  List<String> pictures;


@NotBlank
public String getTitle(){
    return this.title;
}


public void setTitle(String title){
    this.title = title;
}


public void setPictures(List<String> pictures){
    this.pictures = pictures;
}


public void setDescription(String description){
    this.description = description;
}


@NotBlank
public String getDescription(){
    return this.description;
}


@Valid
@ElementCollection(targetClass = String.class)
public List<String> getPictures(){
    return this.pictures;
}


}