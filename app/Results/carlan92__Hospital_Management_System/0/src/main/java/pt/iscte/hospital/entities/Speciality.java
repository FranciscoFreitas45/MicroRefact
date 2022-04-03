package pt.iscte.hospital.entities;
 import lombok.Getter;
import javax.persistence;
import java.util.List;
import pt.iscte.hospital.Request.DoctorRequest;
import pt.iscte.hospital.Request.Impl.DoctorRequestImpl;
import pt.iscte.hospital.DTO.Doctor;
@Entity
@Getter
public class Speciality {

@Id
// https://www.baeldung.com/hibernate-identifiers
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long specialityId;

 private  String name;

 private  double price;

@Transient
 private  List<Doctor> doctors;

@Transient
 private DoctorRequest doctorrequest = new DoctorRequestImpl();;

// Constructors
public Speciality() {
}public Speciality(String name) {
    this.name = name;
}public Speciality(String name, double price) {
    this.name = name;
    this.price = price;
}
public void setName(String name){
    this.name = name;
}


public List<Doctor> getDoctors(){
  this.doctors = doctorrequest.getDoctors(this.specialityId);
return this.doctors;
}


public String getName(){
    return name;
}


public void setDoctors(List<Doctor> doctors){
 doctorrequest.setDoctors(doctors,this.specialityId);
}



public Long getSpecialityId(){
    return specialityId;
}


@Override
public String toString(){
    return "Speciality{" + "specialityId=" + specialityId + ", name='" + name + '\'' + ", price=" + price + '}';
}


public void setSpecialityId(Long specialityId){
    this.specialityId = specialityId;
}


}