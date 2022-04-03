package pl.szymanski.sharelibrary.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.entity.Coordinates;
@RestController
@CrossOrigin
public class CoordinatesExchangeController {

@Autowired
 private CoordinatesExchangeService coordinatesexchangeservice;


}