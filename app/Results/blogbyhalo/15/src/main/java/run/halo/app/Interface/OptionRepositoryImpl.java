package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.OptionRepository;
public class OptionRepositoryImpl implements OptionRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public Optional<Option> findByKey(String key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByKey"))
    .queryParam("key",key)
;  Optional<Option> aux = restTemplate.getForObject(builder.toUriString(), Optional<Option>.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}