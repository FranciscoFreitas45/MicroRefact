package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.TransactionService;
public class TransactionServiceImpl implements TransactionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public TransferOutDTO transferBetweenCashAccounts(TransferDTO transferDTO){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/transferBetweenCashAccounts"))
    .queryParam("transferDTO",transferDTO)
;  TransferOutDTO aux = restTemplate.getForObject(builder.toUriString(), TransferOutDTO.class);

 return aux;
}


}