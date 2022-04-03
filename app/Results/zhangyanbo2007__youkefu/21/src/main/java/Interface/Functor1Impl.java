package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.Functor1;
public class Functor1Impl implements Functor1{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://20";


}