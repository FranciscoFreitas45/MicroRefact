package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class BankStatementReconciliationViewFactoryImpl implements BankStatementReconciliationViewFactory{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public BankStatementReconciliationView createView(BanksStatementReconciliationViewCreateRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createView"))
    .queryParam("request",request);
  BankStatementReconciliationView aux = restTemplate.getForObject(builder.toUriString(), BankStatementReconciliationView.class);

 return aux;
}


}