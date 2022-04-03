package io.delivery.entity;
 import javax.persistence;
@Entity
@Table(name = "customers")
public class Customer {

@Id
// @Column
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

 private  String name;

 private  String address;

@Column(unique = true, name = "phone_number")
 private  String phoneNumber;

@Column(unique = true, name = "e_mail")
 private  String eMail;


public void setName(String name){
    this.name = name;
}


public String geteMail(){
    return eMail;
}


public String getName(){
    return name;
}


public void setAddress(String address){
    this.address = address;
}


public void setPhoneNumber(String phoneNumber){
    this.phoneNumber = phoneNumber;
}


public void seteMail(String eMail){
    this.eMail = eMail;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public String getAddress(){
    return address;
}


public String getPhoneNumber(){
    return phoneNumber;
}


}