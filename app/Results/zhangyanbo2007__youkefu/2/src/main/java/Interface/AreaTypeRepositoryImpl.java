package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.AreaTypeRepository;
public class AreaTypeRepositoryImpl implements AreaTypeRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<AreaType> findByOrgi(String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgi"))
    .queryParam("orgi",orgi)
;  List<AreaType> aux = restTemplate.getForObject(builder.toUriString(), List<AreaType>.class);

 return aux;
}


public AreaType findByIdAndOrgi(String id,String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdAndOrgi"))
    .queryParam("id",id)
    .queryParam("orgi",orgi)
;  AreaType aux = restTemplate.getForObject(builder.toUriString(), AreaType.class);

 return aux;
}


}