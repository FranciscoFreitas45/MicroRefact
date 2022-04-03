package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SysDicRepository;
public class SysDicRepositoryImpl implements SysDicRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public SysDic findByCode(String code){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCode"))
    .queryParam("code",code)
;  SysDic aux = restTemplate.getForObject(builder.toUriString(), SysDic.class);

 return aux;
}


}