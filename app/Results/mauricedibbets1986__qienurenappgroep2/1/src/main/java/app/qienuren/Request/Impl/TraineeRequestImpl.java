package app.qienuren.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.DTO.Trainee;
import app.qienuren.Request.TraineeRequest;
public class TraineeRequestImpl implements TraineeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void koppelTrainee(Trainee trainee,long id){
 restTemplate.put("http://0/KlantContactPersoon/{id}/Trainee/koppelTrainee",trainee,id);
 return ;
}


public List<Trainee> getTrainees(long id){
 List<Trainee> aux = restTemplate.getForObject("http://0/KlantContactPersoon/{id}/Trainee/getTrainees",List<Trainee>.class,id);
return aux;
}


public void setTrainees(List<Trainee> trainees,long id){
 restTemplate.put("http://0/KlantContactPersoon/{id}/Trainee/setTrainees",trainees,id);
 return ;
}


}