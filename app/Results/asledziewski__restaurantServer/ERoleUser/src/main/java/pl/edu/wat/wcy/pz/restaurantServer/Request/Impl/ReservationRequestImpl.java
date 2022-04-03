package pl.edu.wat.wcy.pz.restaurantServer.Request.Impl;
 import org.springframework.web.client.RestTemplate;
 import pl.edu.wat.wcy.pz.restaurantServer.DTO.Reservation;
import pl.edu.wat.wcy.pz.restaurantServer.Request.ReservationRequest;

 import java.util.ArrayList;
 import java.util.List;

public class ReservationRequestImpl implements ReservationRequest{

 private RestTemplate restTemplate = new RestTemplate();

 public void setReservations(List<Reservation> reservations, Long userId){
  restTemplate
          .put("http://6/User/{id}/Reservation/setReservations",
          reservations, userId);
 }

 public List<Reservation> getReservations(Long userId){
  List<Reservation> aux = restTemplate
          .getForObject("http://6/User/{id}/Reservation/getReservations",
                  List.class,userId);
  return aux;
 }
}


