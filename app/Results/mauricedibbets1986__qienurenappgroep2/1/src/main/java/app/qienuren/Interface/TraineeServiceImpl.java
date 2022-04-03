package app.qienuren.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.Interface.TraineeService;
public class TraineeServiceImpl implements TraineeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Trainee addTrainee(Trainee trainee){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addTrainee"))
    .queryParam("trainee",trainee)
;  Trainee aux = restTemplate.getForObject(builder.toUriString(), Trainee.class);

 return aux;
}


public Iterable<Trainee> getAllTrainees(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllTrainees"))
;  Iterable<Trainee> aux = restTemplate.getForObject(builder.toUriString(), Iterable<Trainee>.class);

 return aux;
}


public Trainee traineeKoppelContactPersoon(long traineeID,long kcpID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/traineeKoppelContactPersoon"))
    .queryParam("traineeID",traineeID)
    .queryParam("kcpID",kcpID)
;  Trainee aux = restTemplate.getForObject(builder.toUriString(), Trainee.class);

 return aux;
}


public Trainee wijzigGegevens(long oorspronkelijkeId,long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/wijzigGegevens"))
    .queryParam("oorspronkelijkeId",oorspronkelijkeId)
    .queryParam("id",id)
;  Trainee aux = restTemplate.getForObject(builder.toUriString(), Trainee.class);

 return aux;
}


}