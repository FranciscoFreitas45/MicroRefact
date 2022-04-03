package pl.edu.wat.wcy.pz.restaurantServer.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Reservation;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{


public List<Reservation> findByDateDays(String dateDays)
;

public void setReservations(Long rTableId,List<Reservation> reservations);

public List<Reservation> getReservations(Long rTableId);


}