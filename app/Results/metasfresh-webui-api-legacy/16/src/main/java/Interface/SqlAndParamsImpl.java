package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class SqlAndParamsImpl implements SqlAndParams{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public SqlAndParams of(CharSequence sql,Object sqlParamsArray){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("sql",sql)
    .queryParam("sqlParamsArray",sqlParamsArray);
  SqlAndParams aux = restTemplate.getForObject(builder.toUriString(), SqlAndParams.class);

 return aux;
}


}