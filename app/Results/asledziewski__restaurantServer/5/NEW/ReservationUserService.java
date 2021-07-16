import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class ReservationUserService {

 private ReservationRepository reservationrepository;


public void setReservations(Long userId,List<Reservation> reservations){
reservationrepository.setReservations(userId,reservations);
}


public List<Reservation> getReservations(Long userId){
return reservationrepository.getReservations(userId);
}


}