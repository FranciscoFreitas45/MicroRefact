package es.us.isa.ideas.app.DTO;
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


}