package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.BasePostMinimalDTO;
public class BasePostMinimalDTOImpl implements BasePostMinimalDTO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


}