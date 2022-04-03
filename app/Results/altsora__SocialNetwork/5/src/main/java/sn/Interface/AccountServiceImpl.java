package sn.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import sn.Interface.AccountService;
public class AccountServiceImpl implements AccountService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}