package pt.iscte.hospital.Request;
import pt.iscte.hospital.DTO.Doctor;
public interface DoctorRequest {

   public List<Doctor> getDoctors(Long specialityId);
   public void setDoctors(List<Doctor> doctors,Long specialityId);
}