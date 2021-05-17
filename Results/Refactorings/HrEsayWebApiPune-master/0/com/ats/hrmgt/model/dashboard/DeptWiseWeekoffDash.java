import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class DeptWiseWeekoffDash {

@Id
 private  String id;

 private  int departId;

 private  String nameSd;

 private  int empCount;


public String getNameSd(){
    return nameSd;
}


public int getDepartId(){
    return departId;
}


public void setNameSd(String nameSd){
    this.nameSd = nameSd;
}


public int getEmpCount(){
    return empCount;
}


public void setDepartId(int departId){
    this.departId = departId;
}


public void setId(String id){
    this.id = id;
}


public void setEmpCount(int empCount){
    this.empCount = empCount;
}


public String getId(){
    return id;
}


@Override
public String toString(){
    return "DeptWiseWeekoffDash [id=" + id + ", departId=" + departId + ", nameSd=" + nameSd + ", empCount=" + empCount + "]";
}


}