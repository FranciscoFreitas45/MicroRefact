package app.qienuren.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.controller.TijdelijkeTraineeRepository;
import app.qienuren.model.Trainee;
@Service
public class TraineeKlantContactPersoonService {

@Autowired
 private TijdelijkeTraineeRepository tijdelijketraineerepository;


public void koppelTrainee(long id,Trainee trainee){
tijdelijketraineerepository.koppelTrainee(id,trainee);
}


public List<Trainee> getTrainees(long id){
return tijdelijketraineerepository.getTrainees(id);
}


public void setTrainees(long id,List<Trainee> trainees){
tijdelijketraineerepository.setTrainees(id,trainees);
}


}