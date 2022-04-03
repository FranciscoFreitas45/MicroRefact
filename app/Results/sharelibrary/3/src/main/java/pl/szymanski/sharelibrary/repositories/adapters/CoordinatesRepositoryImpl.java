package pl.szymanski.sharelibrary.repositories.adapters;
 import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.szymanski.sharelibrary.entity.Coordinates;
import pl.szymanski.sharelibrary.repositories.jpa.CoordinatesJPARepository;
import pl.szymanski.sharelibrary.repositories.ports.CoordinatesRepository;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class CoordinatesRepositoryImpl implements CoordinatesRepository{

 private  CoordinatesJPARepository coordinatesJPARepository;


@Override
public Optional<Coordinates> findByLatitudeAndLongitude(Double latitude,Double longitude){
    return coordinatesJPARepository.findByLatitudeAndLongitude(latitude, longitude).stream().findFirst();
}


@Override
public Coordinates saveCoordinates(Coordinates coordinates){
    return coordinatesJPARepository.saveAndFlush(coordinates);
}


}