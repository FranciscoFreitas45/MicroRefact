package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.BrotherhoodService;
public class BrotherhoodServiceImpl implements BrotherhoodService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Brotherhood loggedBrotherhood(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedBrotherhood"))
;  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public Brotherhood reconstructArea(Brotherhood brotherhood,BindingResult binding){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstructArea"))
    .queryParam("brotherhood",brotherhood)
    .queryParam("binding",binding)
;  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public Brotherhood updateBrotherhood(Brotherhood brotherhood){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateBrotherhood"))
    .queryParam("brotherhood",brotherhood)
;  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


}