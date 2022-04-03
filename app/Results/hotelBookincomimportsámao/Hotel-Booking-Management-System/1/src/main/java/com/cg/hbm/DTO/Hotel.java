package com.cg.hbm.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
public class Hotel {

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

	/**
	 * 
	 * @param city
	 * @param hotel_name
	 * @param address
	 * @param description
	 * @param avg_rate_per_day
	 * @param email
	 * @param phone1
	 * @param phone2
	 * @param website
	 */

	public Hotel(String city, String hotel_name, String address, String description, double avg_rate_per_day,
			String email, String phone1, String phone2, String website) {
		super();
		 this.hotel_id=1;
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


public double getAvg_rate_per_day(){
    return avg_rate_per_day;
}


public String getWebsite(){
    return website;
}


public String getDescription(){
    return description;
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


public String getCity(){
    return city;
}

public void setHotel_id(int hotel_id){
	this.hotel_id = hotel_id;
}

	@Override
	public String toString() {
		return "Hotel{" +
				"hotel_id=" + hotel_id +
				", city='" + city + '\'' +
				", hotel_name='" + hotel_name + '\'' +
				", address='" + address + '\'' +
				", description='" + description + '\'' +
				", avg_rate_per_day=" + avg_rate_per_day +
				", email='" + email + '\'' +
				", phone1='" + phone1 + '\'' +
				", phone2='" + phone2 + '\'' +
				", website='" + website + '\'' +
				'}';
	}
}