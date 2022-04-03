package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.JdbcDao;
public class JdbcDaoImpl implements JdbcDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public int[] batchUpdate(String sql,List<Object[]> batch){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/batchUpdate"))
    .queryParam("sql",sql)
    .queryParam("batch",batch)
;  int[] aux = restTemplate.getForObject(builder.toUriString(), int[].class);

 return aux;
}


public Object execute(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/execute"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}