package ink.champ.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import ink.champ.Interface.Sport;
public class SportImpl implements Sport{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


}