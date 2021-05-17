import lombok.Getter;
import javax.persistence;
import java.util.List;
public class Speciality {

 private  Long specialityId;

 private  String name;

 private  double price;

 private  List<Doctor> doctors;


public List<Doctor> getDoctors(){
    return doctors;
}


public String getName(){
    return name;
}


public Long getSpecialityId(){
    return specialityId;
}


}