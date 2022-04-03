package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.LedgerID;
public class LedgerIDImpl implements LedgerID{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


}