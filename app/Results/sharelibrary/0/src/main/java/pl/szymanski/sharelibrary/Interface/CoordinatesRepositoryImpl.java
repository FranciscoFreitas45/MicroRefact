package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.CoordinatesRepository;
public class CoordinatesRepositoryImpl implements CoordinatesRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Coordinates saveCoordinates(Coordinates coordinates){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveCoordinates"))
    .queryParam("coordinates",coordinates)
;  Coordinates aux = restTemplate.getForObject(builder.toUriString(), Coordinates.class);

 return aux;
}


public Optional<Coordinates> findByLatitudeAndLongitude(Double latitude,Double longitude){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByLatitudeAndLongitude"))
    .queryParam("latitude",latitude)
    .queryParam("longitude",longitude)
;  Optional<Coordinates> aux = restTemplate.getForObject(builder.toUriString(), Optional<Coordinates>.class);

 return aux;
}


}