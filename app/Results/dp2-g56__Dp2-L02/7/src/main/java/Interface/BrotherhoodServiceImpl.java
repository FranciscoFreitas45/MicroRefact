package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.BrotherhoodService;
public class BrotherhoodServiceImpl implements BrotherhoodService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public void deleteBrotherhood(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteBrotherhood"))
;
  restTemplate.put(builder.toUriString(), null);
}


}