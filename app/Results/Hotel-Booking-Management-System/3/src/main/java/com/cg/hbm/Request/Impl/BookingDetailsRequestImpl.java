package com.cg.hbm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.DTO.BookingDetails;
import com.cg.hbm.Request.BookingDetailsRequest;
public class BookingDetailsRequestImpl implements BookingDetailsRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setBookingdetails(BookingDetails bookingdetails,int booking_id){
 restTemplate.put("http://localhost:8081/Payments/{id}/BookingDetails/setBookingdetails",bookingdetails,booking_id);
 return ;
}


public BookingDetails getBookingdetails(int booking_id){
 BookingDetails aux = restTemplate.getForObject("http://localhost:8081/Payments/{id}/BookingDetails/getBookingdetails",BookingDetails.class,booking_id);
return aux;
}


}