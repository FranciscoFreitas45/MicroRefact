package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.PositionService;
public class PositionServiceImpl implements PositionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public List<Position> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<Position> aux = restTemplate.getForObject(builder.toUriString(), List<Position>.class);

 return aux;
}


}