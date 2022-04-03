package com.gp.cricket.DTO;
 import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
public class Stadium {

 private  Integer stadiumId;

 private  String name;

 private  String address;

 private  String contactnum;

 private  Integer capacity;

public Stadium() {
    super();
// TODO Auto-generated constructor stub
}public Stadium(Integer stadiumId, @NotBlank(message = "Stadium name is mandatory") String name, @NotBlank(message = "Address is mandatory") String address, @NotBlank(message = "Contact number is mandatory") @Size(min = 10, max = 10, message = "Contact number size should be 10") String contactnum, @NotBlank(message = "Capacity is mandatory") Integer capacity) {
    super();
    this.stadiumId = stadiumId;
    this.name = name;
    this.address = address;
    this.contactnum = contactnum;
    this.capacity = capacity;
}
public Integer getStadiumId(){
    return stadiumId;
}


public String getName(){
    return name;
}


public String getAddress(){
    return address;
}


public String getContactnum(){
    return contactnum;
}


public Integer getCapacity(){
    return capacity;
}


}