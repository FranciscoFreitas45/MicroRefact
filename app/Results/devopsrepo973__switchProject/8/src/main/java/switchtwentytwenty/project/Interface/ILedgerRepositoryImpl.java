package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.ILedgerRepository;
public class ILedgerRepositoryImpl implements ILedgerRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void save(Ledger entity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("entity",entity)
;
  restTemplate.put(builder.toUriString(), null);
}


}