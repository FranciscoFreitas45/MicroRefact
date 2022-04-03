package pl.edu.wat.wcy.pz.restaurantServer.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Reservation;
import javax.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
@Transactional
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{


public List<Reservation> findByDateDays(String dateDays)
;

@Modifying
@Query(value = "update reservation r set r.reservation_id = ?1 where r.reservation_id = ?2", nativeQuery = true)
public void setReservations(Long rTableId,List<Reservation> reservations);

@Query(value = "Select * from reservation r  where rtable_id = ?1", nativeQuery = true)
public List<Reservation> getReservations(Long rTableId);

@Modifying
@Query(value = "update reservation r set r.reservation_id = ?1 where r.reservation_id = ?2", nativeQuery = true)
public void setReservations2(Long userId,List<Reservation> reservations);


@Query(value = "Select * from reservation r where user_id = ?1", nativeQuery = true)
public List<Reservation> getReservations2(Long userId);

}