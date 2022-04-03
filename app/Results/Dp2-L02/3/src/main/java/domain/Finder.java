package domain;
 import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity{

 private  String keyWord;

 private  String area;

 private  Date minDate;

 private  Date maxDate;

 private  Date lastEdit;

 private  List<Parade> parades;


@Valid
public String getKeyWord(){
    return this.keyWord;
}


@Temporal(TemporalType.TIMESTAMP)
@Valid
@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
public Date getLastEdit(){
    return this.lastEdit;
}


public void setKeyWord(String keyWord){
    this.keyWord = keyWord;
}


public void setParades(List<Parade> parades){
    this.parades = parades;
}


@Valid
@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
public Date getMinDate(){
    return this.minDate;
}


public void setMaxDate(Date maxDate){
    this.maxDate = maxDate;
}


public void setArea(String area){
    this.area = area;
}


@Temporal(TemporalType.TIMESTAMP)
@Valid
@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
public Date getMaxDate(){
    return this.maxDate;
}


public void setLastEdit(Date lastEdit){
    this.lastEdit = lastEdit;
}


@Valid
@ManyToMany
public List<Parade> getParades(){
    return this.parades;
}


@Valid
public String getArea(){
    return this.area;
}


public void setMinDate(Date minDate){
    this.minDate = minDate;
}


}