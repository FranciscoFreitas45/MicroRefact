package ar.com.veterinaria.app.entities.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.repository.ClinicalHistoryRepository;
import ar.com.veterinaria.app.entities.ClinicalHistory;
@Service
public class ClinicalHistoryPetService {

@Autowired
 private ClinicalHistoryRepository clinicalhistoryrepository;


public void setClinicalHistory(Integer id,ClinicalHistory clinicalHistory){
clinicalhistoryrepository.setClinicalHistory(id,clinicalHistory);
}


public ClinicalHistory getClinicalHistory(Integer id){
return clinicalhistoryrepository.getClinicalHistory(id);
}


}