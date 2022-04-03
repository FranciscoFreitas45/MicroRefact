package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SponsorshipService;
public class SponsorshipServiceImpl implements SponsorshipService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


}