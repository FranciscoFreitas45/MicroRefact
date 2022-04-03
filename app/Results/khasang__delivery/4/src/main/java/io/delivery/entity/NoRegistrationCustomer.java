package io.delivery.entity;
 import javax.persistence;
@Entity
@Table(name = "no_regitration_customer")
public class NoRegistrationCustomer {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

 private  String name;

 private  String phoneNumber;

 private  String address;

public NoRegistrationCustomer() {
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setAddress(String adress){
    this.address = adress;
}


public void setPhoneNumber(String phoneNumber){
    this.phoneNumber = phoneNumber;
}


public void setId(long id){
    this.id = id;
}


public String getAddress(){
    return address;
}


public String getPhoneNumber(){
    return phoneNumber;
}


public long getId(){
    return id;
}


}