package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CgformFtlServiceI;
public class CgformFtlServiceIImpl implements CgformFtlServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public Map<String,Object> getCgformFtlByTableName(String tableName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCgformFtlByTableName"))
    .queryParam("tableName",tableName);
  Map<String,Object> aux = restTemplate.getForObject(builder.toUriString(), Map<String,Object>.class);

 return aux;
}


}