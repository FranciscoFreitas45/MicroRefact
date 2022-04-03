package es.us.isa.ideas.app.DTO;
 import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import es.us.isa.ideas.app.security.UserAccount;
import javax.persistence.FetchType;
public class Actor extends DomainEntityimplements Serializable{

 private  String name;

 protected  String email;

 private  String phone;

 private  String address;

 private  UserAccount userAccount;

// Constructors -----------------------------------------------------------
public Actor() {
    super();
}
@NotBlank
@Pattern(regexp = "^([+-]\\d+\\s+)?(\\([0-9]+\\)\\s+)?([\\d\\w\\s-]+)$")
public String getPhone(){
    return phone;
}


@NotBlank
public String getName(){
    return name;
}


@Valid
@OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY)
public UserAccount getUserAccount(){
    return userAccount;
}


@NotBlank
@Email
public String getEmail(){
    return email;
}


@NotNull
public String getAddress(){
    return address;
}


}