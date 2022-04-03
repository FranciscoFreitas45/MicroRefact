package ar.com.veterinaria.app.entities.helper.daoImpl;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.ClinicalHistory;
import ar.com.veterinaria.app.entities.helper.daoImpl.contract.ClinicalHistoryContractDaoImplHelper;
@Service
public class ClinicalHistoryDaoImplHelper implements ClinicalHistoryContractDaoImplHelper{

 private  Logger logger;


@Override
public boolean deleted(JpaRepository<ClinicalHistory,Integer> repository,Integer id){
    List<ClinicalHistory> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        ClinicalHistory cClinicalHistory = result.get(ini);
        if (cClinicalHistory.getId().equals(id) && cClinicalHistory.isDeleted()) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public boolean isDuplicated(JpaRepository<ClinicalHistory,Integer> repository,ClinicalHistory clinicalHistory){
    List<ClinicalHistory> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        ClinicalHistory cClinicalHistory = result.get(ini);
        if (cClinicalHistory.getClinicalHistory().equals(clinicalHistory.getClinicalHistory())) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public ClinicalHistory findByName(JpaRepository<ClinicalHistory,Integer> repository,String t){
    List<ClinicalHistory> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        ClinicalHistory cClinicalHistory = result.get(ini);
        if (cClinicalHistory.getClinicalHistory().equals(t)) {
            return cClinicalHistory;
        }
        ini++;
    }
    return null;
}


@Override
public ClinicalHistory update(JpaRepository<ClinicalHistory,Integer> repository,Integer id,ClinicalHistory clinicalHistory){
    List<ClinicalHistory> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        ClinicalHistory cClinicalHistory = result.get(ini);
        if (cClinicalHistory.getId().equals(id)) {
            return cClinicalHistory;
        }
        ini++;
    }
    return null;
}


@Override
public boolean existId(JpaRepository<ClinicalHistory,Integer> repository,Integer id){
    List<ClinicalHistory> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        ClinicalHistory cClinicalHistory = result.get(ini);
        if (cClinicalHistory.getId().equals(id)) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public List<Map<String,Object>> findAll(JpaRepository<ClinicalHistory,Integer> repository){
    try {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<ClinicalHistory> result = repository.findAll();
        for (ClinicalHistory clinicalHistory : result) {
            map = new HashMap<>();
            if (!clinicalHistory.isDeleted()) {
                map.put(clinicalHistory.getId().toString(), clinicalHistory);
                list.add(map);
            }
        }
        return list;
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    return null;
}


}