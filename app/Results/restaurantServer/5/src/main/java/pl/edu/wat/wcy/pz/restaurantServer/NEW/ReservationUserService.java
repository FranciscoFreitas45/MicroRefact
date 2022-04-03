package pl.edu.wat.wcy.pz.restaurantServer.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.repository.ReservationRepository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Reservation;
import java.util.*;
@Service
public class ReservationUserService {

@Autowired
 private ReservationRepository reservationrepository;


public void setReservations(Long userId,List<Reservation> reservations){
reservationrepository.setReservations(userId,reservations);
}


public List<Reservation> getReservations(Long userId){
return reservationrepository.getReservations(userId);
}


}