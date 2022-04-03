package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.BrotherhoodService;
public class BrotherhoodServiceImpl implements BrotherhoodService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Brotherhood createBrotherhood(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createBrotherhood"))
;  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public Brotherhood reconstruct(FormObjectBrotherhood formObjectBrotherhood,BindingResult binding){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstruct"))
    .queryParam("formObjectBrotherhood",formObjectBrotherhood)
    .queryParam("binding",binding)
;  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public Brotherhood saveCreate(Brotherhood bro){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveCreate"))
    .queryParam("bro",bro)
;  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


}