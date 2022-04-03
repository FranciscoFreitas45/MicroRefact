package ar.com.veterinaria.app.entities.JPAImpl;
 import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ar.com.veterinaria.app.entities.ClinicalHistory;
import ar.com.veterinaria.app.entities.dao.ClinicalHistoryDao;
import ar.com.veterinaria.app.entities.exception.notFound.ClinicalHistoryNotFoundException;
import ar.com.veterinaria.app.entities.helper.daoImpl.ClinicalHistoryManagerDaoImplHelper;
import ar.com.veterinaria.app.entities.repository.ClinicalHistoryRepository;
@Repository
public class ClinicalHistoryDaoImpl implements ClinicalHistoryDao{

 private  Logger logger;

@Autowired
 private  ClinicalHistoryRepository clinicalHistoryRepository;

public ClinicalHistoryDaoImpl() {
    super();
}
@Override
public ClinicalHistory add(ClinicalHistory clinicalHistory){
    try {
        clinicalHistoryRepository.save(clinicalHistory);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    return clinicalHistory;
}


@Override
public boolean exist(ClinicalHistory clinicalHistory){
    if (!ClinicalHistoryManagerDaoImplHelper.validate(clinicalHistory)) {
        return false;
    }
    return true;
}


@Override
public ClinicalHistory findById(int id){
    try {
        if (!ClinicalHistoryManagerDaoImplHelper.isDeleted(id)) {
            return clinicalHistoryRepository.findClinicalHistoryById(id);
        }
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    throw new ClinicalHistoryNotFoundException(id);
}


@Override
public ClinicalHistory update(int id,ClinicalHistory clinicalHistory){
    if (ClinicalHistoryManagerDaoImplHelper.existId(id)) {
        if (!ClinicalHistoryManagerDaoImplHelper.isDeleted(id)) {
            clinicalHistoryRepository.save(ClinicalHistoryManagerDaoImplHelper.updateClinicalHistory(id, clinicalHistory));
            return clinicalHistory;
        }
    }
    return null;
}


@Override
public List<Map<String,Object>> findAll(){
    return ClinicalHistoryManagerDaoImplHelper.findAll();
}


@Override
public void remove(int id){
    if (!ClinicalHistoryManagerDaoImplHelper.isDeleted(id)) {
        ClinicalHistory clinicalHistory = clinicalHistoryRepository.findClinicalHistoryById(id);
        clinicalHistory.setId(id);
        clinicalHistory.setDeleted(true);
        clinicalHistoryRepository.save(clinicalHistory);
    }
// falta validar id
}


@Override
public ClinicalHistory findClinicalHistoryByName(String breed){
    return ClinicalHistoryManagerDaoImplHelper.findClinicalHistoryByName(breed);
}


}