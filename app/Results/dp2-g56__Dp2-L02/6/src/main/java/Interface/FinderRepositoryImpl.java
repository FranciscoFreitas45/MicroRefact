package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.FinderRepository;
public class FinderRepositoryImpl implements FinderRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Parade> getParadesByArea(String area){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getParadesByArea"))
    .queryParam("area",area)
;  List<Parade> aux = restTemplate.getForObject(builder.toUriString(), List<Parade>.class);

 return aux;
}


}