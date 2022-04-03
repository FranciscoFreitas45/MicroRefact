package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.BrotherhoodService;
public class BrotherhoodServiceImpl implements BrotherhoodService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void loggedAsBrotherhood(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedAsBrotherhood"))
;
  restTemplate.put(builder.toUriString(), null);
}


public Brotherhood loggedBrotherhood(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedBrotherhood"))
;  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public Brotherhood save(Brotherhood h){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("h",h)
;  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public Brotherhood securityAndBrotherhood(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/securityAndBrotherhood"))
;  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public List<Parade> getParadesByBrotherhood(Brotherhood b){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getParadesByBrotherhood"))
    .queryParam("b",b);
  List<Parade> aux = restTemplate.getForObject(builder.toUriString(), List<Parade>.class);

 return aux;
}


}