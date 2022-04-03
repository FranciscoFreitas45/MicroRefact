package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CallOutTaskRepository;
public class CallOutTaskRepositoryImpl implements CallOutTaskRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public Page<CallOutTask> findAll(Specification<CallOutTask> spec,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("spec",spec)
    .queryParam("pageable",pageable)
;  Page<CallOutTask> aux = restTemplate.getForObject(builder.toUriString(), Page<CallOutTask>.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}