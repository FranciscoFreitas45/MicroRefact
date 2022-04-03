package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.TagRepository;
public class TagRepositoryImpl implements TagRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<Tag> findByOrgiAndTagtype(String orgi,String tagtype){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndTagtype"))
    .queryParam("orgi",orgi)
    .queryParam("tagtype",tagtype)
;  List<Tag> aux = restTemplate.getForObject(builder.toUriString(), List<Tag>.class);

 return aux;
}


}