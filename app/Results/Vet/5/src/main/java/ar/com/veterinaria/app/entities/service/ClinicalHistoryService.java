package ar.com.veterinaria.app.entities.service;
 import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.ClinicalHistory;
import ar.com.veterinaria.app.entities.dao.ClinicalHistoryDao;
import ar.com.veterinaria.app.entities.helper.service.ClinicalHistoryManagerServiceHelper;
import ar.com.veterinaria.app.entities.service.contract.ClinicalHistoryContractService;
@Service
@Transactional
public class ClinicalHistoryService implements ClinicalHistoryContractService{

@Autowired
 private  ClinicalHistoryDao clinicalHistory;

public ClinicalHistoryService() {
}
@Override
public ClinicalHistory add(ClinicalHistory t){
    if (t == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    return clinicalHistory.add(t);
}


@Override
public boolean exist(ClinicalHistory breed){
    if (breed == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    return clinicalHistory.exist(breed);
}


@Override
public boolean isValidInputData(ClinicalHistory clinicalHistory){
    if (ClinicalHistoryManagerServiceHelper.validate(clinicalHistory)) {
        return true;
    }
    return false;
}


@Override
public ClinicalHistory findById(int id){
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0");
    }
    return clinicalHistory.findById(id);
}


@Override
public ClinicalHistory update(int id,ClinicalHistory breed){
    if (breed == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0 or this id do not exist");
    }
    return clinicalHistory.update(id, breed);
}


@Override
public List<Map<String,Object>> findAll(){
    List<Map<String, Object>> result = clinicalHistory.findAll();
    if (result.size() > 0) {
        return result;
    }
    return null;
}


@Override
public void remove(int id){
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0 or this id do not exist");
    }
    clinicalHistory.remove(id);
}


}