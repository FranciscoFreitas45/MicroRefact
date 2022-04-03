package DTO;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
public class PeriodRecord extends DomainEntity{

 private  String title;

 private  String description;

 private  Integer startYear;

 private  Integer endYear;

 private  List<String> photos;

public PeriodRecord() {
    super();
}
@NotNull
@Min(1)
public Integer getEndYear(){
    return this.endYear;
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


@NotNull
@Min(1)
public Integer getStartYear(){
    return this.startYear;
}


@NotBlank
public String getDescription(){
    return this.description;
}


}