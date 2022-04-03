package pl.edu.wat.wcy.pz.restaurantServer.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Reservation;
import pl.edu.wat.wcy.pz.restaurantServer.Request.ReservationRequest;

 import java.util.List;

   public class ReservationRequestImpl implements ReservationRequest{

    private final RestTemplate restTemplate = new RestTemplate();


   public void setReservations(List<Reservation> reservations, Long rTableId){
    restTemplate.put("http://5/RTable/{id}/Reservation/setReservations",reservations,rTableId);
    return ;
   }


   public List<Reservation> getReservations(Long rTableId){
    List aux = restTemplate.getForObject("http://5/RTable/{id}/Reservation/getReservations",
                            List.class,rTableId);
   return aux;
   }


   }

   