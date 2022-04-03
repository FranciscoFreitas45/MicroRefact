package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.PatientDao;
import org.jugbd.mnet.domain.Patient;
@Service
public class PatientVitalService {

@Autowired
 private PatientDao patientdao;


public Vital setPatient(Long id,Patient patient){
return patientdao.setPatient(id,patient);
}


public Patient getPatient(Long id){
return patientdao.getPatient(id);
}


}