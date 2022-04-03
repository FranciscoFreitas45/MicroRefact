package pl.szymanski.sharelibrary.repositories.jpa;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szymanski.sharelibrary.entity.Exchange;
import pl.szymanski.sharelibrary.enums.ExchangeStatus;
import java.util.List;
public interface ExchangeJPARepository extends JpaRepository<Exchange, Long>{


public List<Exchange> findAllByExchangeStatus(ExchangeStatus exchangeStatus)
;

@Query(value = "SELECT * FROM Exchange e JOIN Coordinates c ON e.COORDINATES_ID = c.ID WHERE c.latitude BETWEEN :latMin AND :latMax AND c.longitude BETWEEN :longMin AND :longMax", nativeQuery = true)
public List<Exchange> findByBoundingCoordinates(double latMin,double latMax,double longMin,double longMax)
;

@Query(value = "SELECT * FROM Exchange e WHERE e.user_id = :userId OR e.with_user_id = :userId", nativeQuery = true)
public List<Exchange> findAllLinkedWithUser(Long userId)
;

@Query(value = "SELECT * FROM Exchange e JOIN Coordinates c ON e.COORDINATES_ID = c.ID WHERE ( " + "    acos(" + "        sin(c.latitude * 0.0175) * sin(:latitude * 0.0175) + " + "        cos(c.latitude * 0.0175) * cos(:latitude * 0.0175) * " + "        cos ((:longitude * 0.0175) - (c.longitude * 0.0175)) " + "    ) * 6371 <= :radius" + ")", nativeQuery = true)
public List<Exchange> findByLatitudeAndLongitudeAndRadius(Double latitude,Double longitude,Double radius)
;

}