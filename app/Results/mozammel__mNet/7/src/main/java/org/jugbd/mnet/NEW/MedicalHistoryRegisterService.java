package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.MedicalHistoryDao;
import org.jugbd.mnet.domain.MedicalHistory;
@Service
public class MedicalHistoryRegisterService {

@Autowired
 private MedicalHistoryDao medicalhistorydao;


public MedicalHistory getMedicalHistory(Long id){
return medicalhistorydao.getMedicalHistory(id);
}


public void setMedicalHistory(Long id,MedicalHistory medicalHistory){
medicalhistorydao.setMedicalHistory(id,medicalHistory);
}


}