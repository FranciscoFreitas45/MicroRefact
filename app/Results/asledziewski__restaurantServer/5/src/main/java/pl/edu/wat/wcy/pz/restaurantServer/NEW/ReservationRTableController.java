package pl.edu.wat.wcy.pz.restaurantServer.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Reservation;

 import java.util.List;

  @RestController
  @CrossOrigin
  public class ReservationRTableController {

  @Autowired
   private ReservationRTableService reservationrtableservice;


  @PutMapping
  ("/RTable/{id}/Reservation/setReservations")
  public void setReservations(@PathVariable(name="id") Long rTableId,
                              @RequestBody List<Reservation> reservations){
  reservationrtableservice.setReservations(rTableId,reservations);
  }


  @GetMapping
  ("/RTable/{id}/Reservation/getReservations")
  public List<Reservation> getReservations(@PathVariable(name="id") Long rTableId){
  return reservationrtableservice.getReservations(rTableId);
  }


  }