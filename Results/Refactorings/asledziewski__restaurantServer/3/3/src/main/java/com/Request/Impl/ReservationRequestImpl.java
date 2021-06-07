package com.Request.Impl;

import com.DTO.Reservation;
import com.Request.ReservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ReservationRequestImpl implements ReservationRequest {
 private RestTemplate restTemplate = new RestTemplate();


public void setReservations(List<Reservation> reservations, Long rTableId){
 restTemplate.put("http://5/RTable/{id}/Reservation/setReservations",reservations,rTableId);
 return ;
}


public List<Reservation> getReservations(Long rTableId){
 Reservation[] aux = restTemplate.getForObject("http://5/RTable/{id}/Reservation/getReservations",Reservation[].class,rTableId);
return Arrays.asList(aux);
}


}