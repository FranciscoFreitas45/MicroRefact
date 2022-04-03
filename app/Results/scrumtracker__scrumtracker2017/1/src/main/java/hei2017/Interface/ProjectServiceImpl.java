package hei2017.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.Interface.ProjectService;
public class ProjectServiceImpl implements ProjectService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Project findOneById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOneById"))
    .queryParam("id",id)
;  Project aux = restTemplate.getForObject(builder.toUriString(), Project.class);

 return aux;
}


}