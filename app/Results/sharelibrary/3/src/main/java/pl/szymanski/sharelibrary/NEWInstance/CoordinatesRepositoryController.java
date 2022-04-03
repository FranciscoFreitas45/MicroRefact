package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CoordinatesRepositoryController {

 private CoordinatesRepository coordinatesrepository;


@GetMapping
("/saveCoordinates")
public Coordinates saveCoordinates(@RequestParam(name = "coordinates") Coordinates coordinates){
  return coordinatesrepository.saveCoordinates(coordinates);
}


@GetMapping
("/findByLatitudeAndLongitude")
public Optional<Coordinates> findByLatitudeAndLongitude(@RequestParam(name = "latitude") Double latitude,@RequestParam(name = "longitude") Double longitude){
  return coordinatesrepository.findByLatitudeAndLongitude(latitude,longitude);
}


}