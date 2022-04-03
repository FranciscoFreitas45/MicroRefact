package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.OrgiSkillRelRepository;
public class OrgiSkillRelRepositoryImpl implements OrgiSkillRelRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<OrgiSkillRel> findByOrgi(String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgi"))
    .queryParam("orgi",orgi)
;  List<OrgiSkillRel> aux = restTemplate.getForObject(builder.toUriString(), List<OrgiSkillRel>.class);

 return aux;
}


}