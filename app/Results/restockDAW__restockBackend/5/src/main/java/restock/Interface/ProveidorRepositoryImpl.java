package restock.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import restock.Interface.ProveidorRepository;
public class ProveidorRepositoryImpl implements ProveidorRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Proveidor findById(Integer id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  Proveidor aux = restTemplate.getForObject(builder.toUriString(), Proveidor.class);

 return aux;
}


}