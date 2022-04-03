package pl.szymanski.sharelibrary.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.repositories.jpa.CoordinatesJPARepository;
import pl.szymanski.sharelibrary.entity.Coordinates;
@Service
public class CoordinatesExchangeService {

@Autowired
 private CoordinatesJPARepository coordinatesjparepository;


}