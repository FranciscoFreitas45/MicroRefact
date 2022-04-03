package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.FinderService;
public class FinderServiceImpl implements FinderService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Finder> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<Finder> aux = restTemplate.getForObject(builder.toUriString(), List<Finder>.class);

 return aux;
}


}