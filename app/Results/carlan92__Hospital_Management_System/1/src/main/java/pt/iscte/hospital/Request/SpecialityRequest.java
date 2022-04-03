package pt.iscte.hospital.Request;
import pt.iscte.hospital.DTO.Speciality;
public interface SpecialityRequest {

   public Speciality getSpeciality(Long specialityId);
   public void setSpeciality(Speciality speciality,Long specialityId);
}