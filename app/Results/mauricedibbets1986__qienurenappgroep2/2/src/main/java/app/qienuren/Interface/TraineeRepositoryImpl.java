package app.qienuren.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.Interface.TraineeRepository;
public class TraineeRepositoryImpl implements TraineeRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}