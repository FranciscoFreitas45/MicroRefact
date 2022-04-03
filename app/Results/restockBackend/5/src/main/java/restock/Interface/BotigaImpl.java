package restock.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import restock.DTO.Botiga;
import restock.Interface.Botiga;
public class BotigaImpl implements Botiga{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


}