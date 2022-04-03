package pl.szymanski.sharelibrary.DTO;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.Coordinates;
public class CoordinatesResponse {

 private  Long id;

 private  Double latitude;

 private  Double longitude;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public CoordinatesResponse of(Coordinates coordinates){
    return new CoordinatesResponse(coordinates.getId(), coordinates.getLatitude(), coordinates.getLongitude());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("coordinates",coordinates)
;
CoordinatesResponse aux = restTemplate.getForObject(builder.toUriString(),CoordinatesResponse.class);
return aux;
}


}