package pt.iscte.hospital.Request;
import pt.iscte.hospital.DTO.Patient;
public interface PatientRequest {

   public void setPatient(Patient patient,Long userId);
   public Patient getPatient(Long userId);
}