package pt.iscte.hospital.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.repositories.waiting.DoctorWaitingPatientRepository;
import pt.iscte.hospital.entities.waiting.DoctorWaitingPatient;
@Service
public class DoctorWaitingPatientAppointmentService {

@Autowired
 private DoctorWaitingPatientRepository doctorwaitingpatientrepository;


public DoctorWaitingPatient getDoctorWaitingPatient(Long doctorWaitingPatientId){
return doctorwaitingpatientrepository.getDoctorWaitingPatient(doctorWaitingPatientId);
}


public void setDoctorWaitingPatient(Long doctorWaitingPatientId,DoctorWaitingPatient doctorWaitingPatient){
doctorwaitingpatientrepository.setDoctorWaitingPatient(doctorWaitingPatientId,doctorWaitingPatient);
}


}