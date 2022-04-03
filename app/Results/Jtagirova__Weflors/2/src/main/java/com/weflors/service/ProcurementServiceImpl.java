package com.weflors.service;
 import com.weflors.entity.ProcurementEntity;
import com.weflors.repository.ProcurementRepository;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;
@Service
public class ProcurementServiceImpl {

 private  ProcurementRepository procurementRepository;

public ProcurementServiceImpl(ProcurementRepository procurementRepository) {
    this.procurementRepository = procurementRepository;
}
public ProcurementEntity findProcurementByProductID(Integer productID){
    return procurementRepository.findProcurementByProductID(productID);
}


public List<ProcurementEntity> findAllProcurementsByProductDate(Integer productId,Timestamp startDatePeriod,Timestamp endDatePeriod){
    return procurementRepository.findAllProcurementsByProductIDAndDatePeriod(productId, startDatePeriod, endDatePeriod);
}


public List<ProcurementEntity> loadAllProcurements(){
    return procurementRepository.findAll();
}


public ProcurementEntity saveProcurement(ProcurementEntity procurementEntity){
    return procurementRepository.save(procurementEntity);
}


public List<ProcurementEntity> findAllProcurementsByProductID(Integer productID){
    return procurementRepository.findAllProcurementsByProductID(productID);
}


public List<ProcurementEntity> findAllProcurementsByDate(Timestamp date){
    return procurementRepository.findAllProcurementsByDate(date);
}


}