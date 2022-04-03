package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.TemplateRepository;
public class TemplateRepositoryImpl implements TemplateRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<Template> findByTemplettypeAndOrgi(String templettype,String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByTemplettypeAndOrgi"))
    .queryParam("templettype",templettype)
    .queryParam("orgi",orgi)
;  List<Template> aux = restTemplate.getForObject(builder.toUriString(), List<Template>.class);

 return aux;
}


public Template findByIdAndOrgi(String id,String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdAndOrgi"))
    .queryParam("id",id)
    .queryParam("orgi",orgi)
;  Template aux = restTemplate.getForObject(builder.toUriString(), Template.class);

 return aux;
}


}