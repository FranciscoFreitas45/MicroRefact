package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.ILedgerIDGenerator;
public class ILedgerIDGeneratorImpl implements ILedgerIDGenerator{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public LedgerID generate(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/generate"))
;  LedgerID aux = restTemplate.getForObject(builder.toUriString(), LedgerID.class);

 return aux;
}


}