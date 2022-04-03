package domain;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotBlank;
@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "name") })
public class Area extends DomainEntity{

 private  String name;

 private  List<String> pictures;


public void setName(String name){
    this.name = name;
}


@NotBlank
public String getName(){
    return this.name;
}


public void setPictures(List<String> pictures){
    this.pictures = pictures;
}


@Valid
@ElementCollection(targetClass = String.class)
public List<String> getPictures(){
    return this.pictures;
}


}