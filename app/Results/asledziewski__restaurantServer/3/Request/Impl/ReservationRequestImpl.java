import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
public class ReservationRequestImpl implements ReservationRequest{

@Autowired
 private RestTemplate restTemplate;


public void setReservations(List<Reservation> reservations,Long rTableId){
 restTemplate.put('http://5/RTable/{id}/Reservation/setReservations',reservations,rTableId);
 return ;
}


public List<Reservation> getReservations(Long rTableId){
 List<Reservation> aux = restTemplate.getForObject('http://5/RTable/{id}/Reservation/getReservations',List<Reservation>.class,rTableId);
return aux;
}


}