package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SysDicRepository;
public class SysDicRepositoryImpl implements SysDicRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<SysDic> findByParentid(String type){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByParentid"))
    .queryParam("type",type)
;  List<SysDic> aux = restTemplate.getForObject(builder.toUriString(), List<SysDic>.class);

 return aux;
}


}