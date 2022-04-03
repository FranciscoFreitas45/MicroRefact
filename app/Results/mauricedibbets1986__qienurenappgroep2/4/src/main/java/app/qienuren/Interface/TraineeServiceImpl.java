package app.qienuren.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.Interface.TraineeService;
public class TraineeServiceImpl implements TraineeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Iterable<Trainee> getTraineesByKCPId(long kcpId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTraineesByKCPId"))
    .queryParam("kcpId",kcpId)
;  Iterable<Trainee> aux = restTemplate.getForObject(builder.toUriString(), Iterable<Trainee>.class);

 return aux;
}


}