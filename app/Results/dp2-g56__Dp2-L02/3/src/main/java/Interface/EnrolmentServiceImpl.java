package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.EnrolmentService;
public class EnrolmentServiceImpl implements EnrolmentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public void delete(Enrolment e){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("e",e)
;
  restTemplate.put(builder.toUriString(), null);
}


}