package pt.iscte.hospital.Request;
import pt.iscte.hospital.DTO.DoctorWaitingPatient;
public interface DoctorWaitingPatientRequest {

   public DoctorWaitingPatient getDoctorWaitingPatient(Long doctorWaitingPatientId);
   public void setDoctorWaitingPatient(DoctorWaitingPatient doctorWaitingPatient,Long doctorWaitingPatientId);
}