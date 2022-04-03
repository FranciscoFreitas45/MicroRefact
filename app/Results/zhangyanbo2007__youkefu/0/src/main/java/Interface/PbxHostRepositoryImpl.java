package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.PbxHostRepository;
public class PbxHostRepositoryImpl implements PbxHostRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public PbxHost findById(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  PbxHost aux = restTemplate.getForObject(builder.toUriString(), PbxHost.class);

 return aux;
}


}