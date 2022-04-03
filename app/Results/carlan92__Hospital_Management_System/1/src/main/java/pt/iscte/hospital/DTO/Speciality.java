package pt.iscte.hospital.DTO;
 import lombok.Getter;
import javax.persistence;
import java.util.List;
import pt.iscte.hospital.Request.DoctorRequest;
import pt.iscte.hospital.Request.Impl.DoctorRequestImpl;
import pt.iscte.hospital.DTO.Doctor;
public class Speciality {

 private  Long specialityId;

 private  String name;

 private  double price;

 private  List<Doctor> doctors;

// Constructors
public Speciality() {
}public Speciality(String name) {
    this.name = name;
}public Speciality(String name, double price) {
    this.name = name;
    this.price = price;
}
public List<Doctor> getDoctors(){
  this.doctors = doctorrequest.getDoctors(this.specialityId);
return this.doctors;
}


public String getName(){
    return name;
}


public Long getSpecialityId(){
    return specialityId;
}


}