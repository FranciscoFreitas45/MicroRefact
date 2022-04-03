package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ParadeService;
public class ParadeServiceImpl implements ParadeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Parade findOne(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id)
;  Parade aux = restTemplate.getForObject(builder.toUriString(), Parade.class);

 return aux;
}


}