import lombok.Getter;
import javax.persistence;
import java.util.List;
public class Speciality {

 private  Long specialityId;

 private  String name;

 private  double price;

 private  List<Doctor> doctors;

 private DoctorRequestImpl doctorrequestimpl;


public List<Doctor> getDoctors(){
  this.doctors = doctorrequestimpl.getDoctors(this.specialityId);
return this.doctors;
}


public String getName(){
    return name;
}


public Long getSpecialityId(){
    return specialityId;
}


}