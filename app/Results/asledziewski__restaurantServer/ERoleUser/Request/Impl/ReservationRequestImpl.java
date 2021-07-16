import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
public class ReservationRequestImpl implements ReservationRequest{

@Autowired
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