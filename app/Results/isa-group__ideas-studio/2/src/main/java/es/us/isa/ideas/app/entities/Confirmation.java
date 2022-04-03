package es.us.isa.ideas.app.entities;
 import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
@Entity
@Access(AccessType.PROPERTY)
public class Confirmation extends DomainEntity{

 private  Date registrationDate;

 private  Date confirmationDate;

 private  String confirmationCode;

 private  Researcher researcher;


@NotNull
@Valid
@OneToOne(optional = false)
public Researcher getResearcher(){
    return researcher;
}


@NotBlank
@Column(unique = true)
public String getConfirmationCode(){
    return confirmationCode;
}


@Temporal(javax.persistence.TemporalType.TIMESTAMP)
public Date getConfirmationDate(){
    return confirmationDate;
}


@NotNull
@Temporal(javax.persistence.TemporalType.TIMESTAMP)
public Date getRegistrationDate(){
    return registrationDate;
}


public void setResearcher(Researcher researcher){
    this.researcher = researcher;
}


public void setConfirmationCode(String confimationCode){
    this.confirmationCode = confimationCode;
}


public void setRegistrationDate(Date registrationDate){
    this.registrationDate = registrationDate;
}


public void setConfirmationDate(Date confirmationDate){
    this.confirmationDate = confirmationDate;
}


}