package domain;
 import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Access(AccessType.PROPERTY)
public class Proclaim extends DomainEntity{

 private  Date publishMoment;

 private  String description;


@NotNull
@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
public Date getPublishMoment(){
    return this.publishMoment;
}


public void setPublishMoment(Date publishMoment){
    this.publishMoment = publishMoment;
}


public void setDescription(String description){
    this.description = description;
}


@NotBlank
@Size(max = 250)
public String getDescription(){
    return this.description;
}


}