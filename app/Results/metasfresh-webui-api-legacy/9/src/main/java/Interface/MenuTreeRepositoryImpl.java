package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class MenuTreeRepositoryImpl implements MenuTreeRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public MenuTree getUserSessionMenuTree(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserSessionMenuTree"))
  MenuTree aux = restTemplate.getForObject(builder.toUriString(), MenuTree.class);

 return aux;
}


public Object getNewRecordNodeForWindowId(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getNewRecordNodeForWindowId"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object map(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/map"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}