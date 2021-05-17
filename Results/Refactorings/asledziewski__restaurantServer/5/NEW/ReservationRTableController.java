import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class ReservationRTableController {

 private ReservationRTableService reservationrtableservice;


@PutMapping
("/RTable/{id}/Reservation/setReservations")
public void setReservations(@PathVariable(name="id") Long rTableId,@RequestBody List<Reservation> reservations){
reservationrtableservice.setReservations(rTableId,reservations);
}


@GetMapping
("/RTable/{id}/Reservation/getReservations")
public List<Reservation> getReservations(@PathVariable(name="id") Long rTableId){
reservationrtableservice.getReservations(rTableId);
}


}