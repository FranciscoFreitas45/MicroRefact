package pl.edu.wat.wcy.pz.restaurantServer.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.repository.ReservationRepository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Reservation;
import java.util.*;
@Service
public class ReservationRTableService {

@Autowired
 private ReservationRepository reservationrepository;


public void setReservations(Long rTableId,List<Reservation> reservations){
reservationrepository.setReservations(rTableId,reservations);
}


public List<Reservation> getReservations(Long rTableId){
return reservationrepository.getReservations(rTableId);
}


}