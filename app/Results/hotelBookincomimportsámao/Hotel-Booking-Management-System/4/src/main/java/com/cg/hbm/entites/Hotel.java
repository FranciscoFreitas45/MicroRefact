package com.cg.hbm.entites;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Hotel")
public class Hotel {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int hotel_id;

 private  String city;

 private  String hotel_name;

 private  String address;

 private  String description;

 private  double avg_rate_per_day;

 private  String email;

 private  String phone1;

 private  String phone2;

 private  String website;


public Hotel() {

	}

public Hotel(String city, String hotel_name, String address, String description, double avg_rate_per_day,
			String email, String phone1, String phone2, String website) {
		super();
		// this.hotel_id=hotel_id;
		this.city = city;
		this.hotel_name = hotel_name;
		this.address = address;
		this.description = description;
		this.avg_rate_per_day = avg_rate_per_day;
		this.email = email;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.website = website;

	}


public int getHotel_id(){
    return hotel_id;
}


public void setWebsite(String website){
    this.website = website;
}


public double getAvg_rate_per_day(){
    return avg_rate_per_day;
}


public String getWebsite(){
    return website;
}


public void setAddress(String address){
    this.address = address;
}


public void setCity(String city){
    this.city = city;
}


public void setHotel_name(String hotel_name){
    this.hotel_name = hotel_name;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setAvg_rate_per_day(double avg_rate_per_day){
    this.avg_rate_per_day = avg_rate_per_day;
}


public void setEmail(String email){
    this.email = email;
}


public String getHotel_name(){
    return hotel_name;
}


public String getEmail(){
    return email;
}


public String getPhone2(){
    return phone2;
}


public String getAddress(){
    return address;
}


public String getPhone1(){
    return phone1;
}


public void setHotel_id(int hotel_id){
    this.hotel_id = hotel_id;
}


public void setPhone1(String phone1){
    this.phone1 = phone1;
}


public void setPhone2(String phone2){
    this.phone2 = phone2;
}


public String getCity(){
    return city;
}


}