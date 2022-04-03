package domain;
 import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "area"), @Index(columnList = "polarity"), @Index(columnList = "history") })
public class Brotherhood extends Actor{

 private  String title;

 private  Date establishmentDate;

 private  List<String> pictures;

 private  Area area;

 private  List<Float> floats;

 private  List<Parade> parades;

 private  List<Enrolment> enrolments;

 private  History history;


public void setParades(List<Parade> parades){
    this.parades = parades;
}


@Valid
@OneToMany
public List<Float> getFloats(){
    return this.floats;
}


public void setArea(Area area){
    this.area = area;
}


public void setEstablishmentDate(Date establishmentDate){
    this.establishmentDate = establishmentDate;
}


public void setTitle(String title){
    this.title = title;
}


public void setEnrolments(List<Enrolment> enrolments){
    this.enrolments = enrolments;
}


public void setHistory(History history){
    this.history = history;
}


@OneToOne(optional = true, cascade = CascadeType.ALL)
@Valid
public History getHistory(){
    return this.history;
}


@NotBlank
public String getTitle(){
    return this.title;
}


@Valid
@OneToMany(mappedBy = "brotherhood")
public List<Enrolment> getEnrolments(){
    return this.enrolments;
}


public void setFloats(List<Float> floats){
    this.floats = floats;
}


@Past
@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
public Date getEstablishmentDate(){
    return this.establishmentDate;
}


public void setPictures(List<String> pictures){
    this.pictures = pictures;
}


@Valid
@OneToMany
public List<Parade> getParades(){
    return this.parades;
}


@Valid
@ElementCollection(targetClass = String.class)
public List<String> getPictures(){
    return this.pictures;
}


@Valid
@ManyToOne(optional = true)
public Area getArea(){
    return this.area;
}


}