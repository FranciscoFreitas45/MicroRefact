package gov.cdc.nccdphp.DTO;
 import lombok.Data;
import org.hibernate.validator.constraints.Email;
import javax.persistence;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
public class Manager {

 public  String REGEXP_PHONE_NUMBER;

 private  Long id;

 private  Long version;

 private  String name;

 private  String email;

 private  String phoneNumber;

 private  Boolean active;


}