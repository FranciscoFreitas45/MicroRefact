package pl.edu.wat.wcy.pz.restaurantServer.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Reservation;
import java.util.*;
@RestController
@CrossOrigin
public class ReservationUserController {

@Autowired
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