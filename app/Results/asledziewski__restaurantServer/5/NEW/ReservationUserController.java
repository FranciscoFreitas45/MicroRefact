import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class ReservationUserController {

 private ReservationUserService reservationuserservice;


@PutMapping
("/User/{id}/Reservation/setReservations")
public void setReservations(@PathVariable(name="id") Long userId,@RequestBody List<Reservation> reservations){
reservationuserservice.setReservations(userId,reservations);
}


@GetMapping
("/User/{id}/Reservation/getReservations")
public List<Reservation> getReservations(@PathVariable(name="id") Long userId){
return reservationuserservice.getReservations(userId);
}


}