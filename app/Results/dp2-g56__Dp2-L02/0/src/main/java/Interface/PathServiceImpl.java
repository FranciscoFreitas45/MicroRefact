package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.PathService;
public class PathServiceImpl implements PathService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Path create(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
;  Path aux = restTemplate.getForObject(builder.toUriString(), Path.class);

 return aux;
}


public Path save(Path path){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("path",path)
;  Path aux = restTemplate.getForObject(builder.toUriString(), Path.class);

 return aux;
}


public void delete(Path path){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("path",path)
;
  restTemplate.put(builder.toUriString(), null);
}


}