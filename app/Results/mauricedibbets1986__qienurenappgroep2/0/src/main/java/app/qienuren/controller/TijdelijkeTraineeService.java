package app.qienuren.controller;
 import app.qienuren.model.TijdelijkeTrainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
@Service
@Transactional
public class TijdelijkeTraineeService {

@Autowired
 private TijdelijkeTraineeRepository tijdelijketraineeRepository;

@Autowired
 private TraineeRepository traineeRepository;


public Iterable<TijdelijkeTrainee> getAllTijdelijkeTrainee(){
    System.out.println("alle tijdelijke trainees opgevraags");
    return tijdelijketraineeRepository.findAll();
}


public TijdelijkeTrainee getTijdelijkeTraineByOorspronkelijkeId(long oorspronkelijkeId){
    ArrayList<TijdelijkeTrainee> alleTijdelijkeTrainees = (ArrayList<TijdelijkeTrainee>) tijdelijketraineeRepository.findAll();
    TijdelijkeTrainee terugTeSturenTijdelijkeTrainee = null;
    for (TijdelijkeTrainee tt : alleTijdelijkeTrainees) {
        if (tt.getOorspronkelijkeId() == oorspronkelijkeId) {
            terugTeSturenTijdelijkeTrainee = tt;
        }
    }
    return terugTeSturenTijdelijkeTrainee;
}


public TijdelijkeTrainee addTijdelijkeTrainee(long traineeID,TijdelijkeTrainee tijdtrainee){
    tijdtrainee.setOorspronkelijkeId(traineeRepository.findById(traineeID).get().getId());
    System.out.println("tijdelijke trainee aangemaakt");
    return tijdelijketraineeRepository.save(tijdtrainee);
}


public TijdelijkeTrainee getTijdelijkeTraineeById(long tijdelijkeTraineeId){
    return tijdelijketraineeRepository.findById(tijdelijkeTraineeId).get();
}


}