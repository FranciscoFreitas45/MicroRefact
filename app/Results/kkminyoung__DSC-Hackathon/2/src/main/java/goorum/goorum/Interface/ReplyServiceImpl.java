package goorum.goorum.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import goorum.goorum.Interface.ReplyService;
public class ReplyServiceImpl implements ReplyService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}