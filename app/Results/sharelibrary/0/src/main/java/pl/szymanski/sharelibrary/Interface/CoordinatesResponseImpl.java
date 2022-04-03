package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.CoordinatesResponse;
public class CoordinatesResponseImpl implements CoordinatesResponse{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public CoordinatesResponse of(Coordinates coordinates){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("coordinates",coordinates)
;  CoordinatesResponse aux = restTemplate.getForObject(builder.toUriString(), CoordinatesResponse.class);

 return aux;
}


}