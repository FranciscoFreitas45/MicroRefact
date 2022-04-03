package io.swagger.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.repository.MedicRepository;
import io.swagger.model.Medic;
@Service
public class MedicSessionService {

@Autowired
 private MedicRepository medicrepository;


public Medic getMedic(Long id){
return medicrepository.getMedic(id);
}


public void setMedic(Long id,Medic medic){
medicrepository.setMedic(id,medic);
}


}