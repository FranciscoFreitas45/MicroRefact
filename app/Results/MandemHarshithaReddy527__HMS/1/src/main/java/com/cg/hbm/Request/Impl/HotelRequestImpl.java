package com.cg.hbm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.DTO.Hotel;
import com.cg.hbm.Request.HotelRequest;
public class HotelRequestImpl implements HotelRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setHotel(Hotel hotel,int hotel_id){
 restTemplate.put("http://4/BookingDetails/{id}/Hotel/setHotel",hotel,hotel_id);
 return ;
}


public Hotel getHotel(int hotel_id){
 Hotel aux = restTemplate.getForObject("http://4/BookingDetails/{id}/Hotel/getHotel",Hotel.class,hotel_id);
return aux;
}


}