package lk.sliit.csse.procurementsystem.models;
 import org.hibernate.validator.constraints.UniqueElements;
import javax.persistence;
@Entity
@Inheritance
public class Employee extends User{

 private  String firstName;

 private  String lastName;

 private  String address;

 private  String phone;


public String getPhone(){
    return phone;
}


public void setLastName(String lastName){
    this.lastName = lastName;
}


public void setAddress(String address){
    this.address = address;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public String getAddress(){
    return address;
}


public void setPhone(String phone){
    this.phone = phone;
}


public String getFirstName(){
    return firstName;
}


public String getLastName(){
    return lastName;
}


}