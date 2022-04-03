package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CallOutFilterRepository;
public class CallOutFilterRepositoryImpl implements CallOutFilterRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Page<CallOutFilter> findAll(Specification<CallOutFilter> spec,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("spec",spec)
    .queryParam("pageable",pageable)
;  Page<CallOutFilter> aux = restTemplate.getForObject(builder.toUriString(), Page<CallOutFilter>.class);

 return aux;
}


}