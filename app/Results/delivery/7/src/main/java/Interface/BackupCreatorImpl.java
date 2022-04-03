package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class BackupCreatorImpl implements BackupCreator{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public String createBackup(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createBackup"))
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}