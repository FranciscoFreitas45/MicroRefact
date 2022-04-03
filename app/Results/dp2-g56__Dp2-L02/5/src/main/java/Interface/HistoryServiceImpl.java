package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.HistoryService;
public class HistoryServiceImpl implements HistoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void deleteAllHistory(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteAllHistory"))
;
  restTemplate.put(builder.toUriString(), null);
}


}