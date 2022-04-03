package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CoordinatesResponseController {

 private CoordinatesResponse coordinatesresponse;


@GetMapping
("/of")
public CoordinatesResponse of(@RequestParam(name = "coordinates") Coordinates coordinates){
  return coordinatesresponse.of(coordinates);
}


}