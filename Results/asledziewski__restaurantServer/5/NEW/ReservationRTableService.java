import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class ReservationRTableService {

 private ReservationRepository reservationrepository;


public void setReservations(Long rTableId,List<Reservation> reservations){
reservationrepository.setReservations(rTableId,reservations);
}


public List<Reservation> getReservations(Long rTableId){
reservationrepository.getReservations(rTableId);
}


}