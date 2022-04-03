package pl.edu.wat.wcy.pz.restaurantServer.Request;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Reservation;

import java.util.List;

      public interface ReservationRequest {

         public void setReservations(List<Reservation> reservations,
                                     Long rTableId);
         public List<Reservation> getReservations(Long rTableId);
      }