package DTO;
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
public class Brotherhood extends Actor{

 private  String title;

 private  Date establishmentDate;

 private  List<String> pictures;

 private  Area area;

 private  List<Float> floats;

 private  List<Parade> parades;

 private  List<Enrolment> enrolments;

 private  History history;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


@Valid
@OneToMany
public List<Float> getFloats(){
    return this.floats;
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


@Past
@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
public Date getEstablishmentDate(){
    return this.establishmentDate;
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


public void setParades(List<Parade> parades){
    this.parades = parades;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setParades"))

.queryParam("parades",parades)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFloats(List<Float> floats){
    this.floats = floats;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFloats"))

.queryParam("floats",floats)
;
restTemplate.put(builder.toUriString(),null);
}


}