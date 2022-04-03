package com.yalcin.dto.request;
 import java.util.Set;
import javax.validation.constraints;
public class ChangeForm {

@NotBlank
@Size(max = 60)
@Email
 private  String email;

@Size(min = 3, max = 25)
 private  String name;

@Size(min = 3, max = 25)
 private  String lastname;

@Size(min = 1, max = 3)
 private  String age;

@Size(min = 10, max = 10)
 private  String phoneNumber;

@Size(min = 10, max = 100)
 private  String adress;


public void setName(String name){
    this.name = name;
}


public void setAdress(String adress){
    this.adress = adress;
}


public String getAge(){
    return age;
}


public void setLastname(String lastname){
    this.lastname = lastname;
}


public String getName(){
    return name;
}


public void setEmail(String email){
    this.email = email;
}


public void setPhoneNumber(String phoneNumber){
    this.phoneNumber = phoneNumber;
}


public String getEmail(){
    return email;
}


public String getPhoneNumber(){
    return phoneNumber;
}


public String getLastname(){
    return lastname;
}


public void setAge(String age){
    this.age = age;
}


public String getAdress(){
    return adress;
}


}