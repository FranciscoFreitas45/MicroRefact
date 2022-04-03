package pl.szymanski.sharelibrary.repositories.ports;
 import pl.szymanski.sharelibrary.entity.Coordinates;
import java.util.Optional;
public interface CoordinatesRepository {


public Optional<Coordinates> findByLatitudeAndLongitude(Double latitude,Double longitude)
;

public Coordinates saveCoordinates(Coordinates coordinates)
;

}