package hei2017.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.Interface.StoryService;
public class StoryServiceImpl implements StoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}