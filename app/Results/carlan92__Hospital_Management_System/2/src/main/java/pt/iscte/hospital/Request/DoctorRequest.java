package pt.iscte.hospital.Request;
import pt.iscte.hospital.DTO.Doctor;
public interface DoctorRequest {

   public Doctor getDoctor(Long userId);
   public void setDoctor(Doctor doctor,Long userId);
}