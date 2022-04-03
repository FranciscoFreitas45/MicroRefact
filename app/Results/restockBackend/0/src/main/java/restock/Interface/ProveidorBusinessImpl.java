package restock.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import restock.DTO.Proveidor;
import restock.Interface.ProveidorBusiness;
import java.util.*;
public class ProveidorBusinessImpl implements ProveidorBusiness{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<Proveidor> getProveidorsPerOrganitzacio(Integer orgId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getProveidorsPerOrganitzacio"))
    .queryParam("orgId",orgId);
  List<Proveidor> aux = restTemplate.getForObject(builder.toUriString(), List.class);

 return aux;
}


}