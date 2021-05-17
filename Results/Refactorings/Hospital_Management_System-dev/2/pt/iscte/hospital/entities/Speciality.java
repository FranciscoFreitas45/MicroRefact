import lombok.Getter;
import javax.persistence;
import java.util.List;
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
 private DoctorRequestImpl doctorrequestimpl;


public void setName(String name){
    this.name = name;
}


public List<Doctor> getDoctors(){
  this.doctors = doctorrequestimpl.getDoctors(this.specialityId);
return this.doctors;
}


public String getName(){
    return name;
}


public void setDoctors(List<Doctor> doctors){
 doctorrequestimpl.setDoctors(doctors,this.specialityId);



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