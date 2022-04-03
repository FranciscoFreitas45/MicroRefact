package gov.cdc.nccdphp.domain;
 import lombok.Data;
import org.hibernate.validator.constraints.Email;
import javax.persistence;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Entity
@Data
public class Manager {

 public  String REGEXP_PHONE_NUMBER;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

@NotNull
 private  String name;

@NotNull
@Email
 private  String email;

@NotNull
@Pattern(regexp = REGEXP_PHONE_NUMBER, message = "{phoneNumber.invalid}")
 private  String phoneNumber;

 private  Boolean active;


}