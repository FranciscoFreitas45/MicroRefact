package ink.champ.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import ink.champ.Interface.Team;
public class TeamImpl implements Team{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}