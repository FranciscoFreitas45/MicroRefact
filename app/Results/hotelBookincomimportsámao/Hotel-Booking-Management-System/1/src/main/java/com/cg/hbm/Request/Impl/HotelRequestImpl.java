package com.cg.hbm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.cg.hbm.DTO.Hotel;
import com.cg.hbm.Request.HotelRequest;

public class HotelRequestImpl implements HotelRequest{

@Autowired
 private RestTemplate restTemplate = new RestTemplate();


public void setHotel(Hotel hotel,int hotel_id){
    System.out.println("ID DO SET  " + hotel_id);
    restTemplate.put("http://127.0.0.1:8084/BookingDetails/{id}/Hotel/setHotel",hotel,hotel_id);
 return ;
}


public Hotel getHotel(int hotel_id){
     try {

      Hotel aux = restTemplate.getForObject("http://127.0.0.1:8084/BookingDetails/{id}/Hotel/getHotel", Hotel.class, hotel_id);
      return aux;
     } catch (Exception e) {
       return null;
     }
}


}