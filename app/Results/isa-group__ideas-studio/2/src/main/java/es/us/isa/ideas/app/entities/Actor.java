package es.us.isa.ideas.app.entities;
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
@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
public void setName(String name){
    this.name = name;
}


@NotBlank
@Pattern(regexp = "^([+-]\\d+\\s+)?(\\([0-9]+\\)\\s+)?([\\d\\w\\s-]+)$")
public String getPhone(){
    return phone;
}


public void setUserAccount(UserAccount userAccount){
    this.userAccount = userAccount;
}


@NotBlank
public String getName(){
    return name;
}


public void setEmail(String email){
    this.email = email;
}


public void setAddress(String address){
    this.address = address;
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


public void setPhone(String phone){
    this.phone = phone;
}


@NotNull
public String getAddress(){
    return address;
}


}