package pl.szymanski.sharelibrary.repositories.jpa;
 import org.springframework.data.jpa.repository.JpaRepository;
import pl.szymanski.sharelibrary.entity.Coordinates;
import java.util.List;
public interface CoordinatesJPARepository extends JpaRepository<Coordinates, Long>{


public List<Coordinates> findByLatitudeAndLongitude(Double latitude,Double longitude)
;

}