import org.springframework.web.client.RestTemplate;
public class ReservationRequestImpl implements ReservationRequest{

 private RestTemplate restTemplate;


public void setReservations(List<Reservation> reservations,Long userId){
 restTemplate.put('http://5/User/{id}/Reservation/setReservations',reservations,userId);
 return ;
}


public List<Reservation> getReservations(Long userId){
 List<Reservation> aux = restTemplate.getForObject('http://5/User/{id}/Reservation/getReservations',List<Reservation>.class,userId);
return aux;
}


}