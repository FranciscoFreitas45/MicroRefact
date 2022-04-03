package hei2017.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.Interface.TaskService;
public class TaskServiceImpl implements TaskService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}