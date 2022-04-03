package edu.nju.careerbridge.youth.model;
 import javax.persistence;
@Entity
@Table(name = "expect_location")
public class ExpectLocation {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
@Id
 private  Integer id;

 private  String phone;

 private  String expectLocation;

public ExpectLocation() {
}public ExpectLocation(String phone, String expectLocation) {
    this.phone = phone;
    this.expectLocation = expectLocation;
}
public String getPhone(){
    return phone;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


public void setPhone(String phone){
    this.phone = phone;
}


public String getExpectLocation(){
    return expectLocation;
}


public void setExpectLocation(String expectLocation){
    this.expectLocation = expectLocation;
}


}