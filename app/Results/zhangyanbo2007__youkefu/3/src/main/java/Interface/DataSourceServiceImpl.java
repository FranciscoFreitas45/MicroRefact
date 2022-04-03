package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.DataSourceService;
public class DataSourceServiceImpl implements DataSourceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://20";


public Connection service(String xml){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/service"))
    .queryParam("xml",xml);
  Connection aux = restTemplate.getForObject(builder.toUriString(), Connection.class);

 return aux;
}


}