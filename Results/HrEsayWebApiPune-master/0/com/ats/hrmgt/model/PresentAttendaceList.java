import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class PresentAttendaceList {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  String id;

 private  int empId;

 private  String empName;

 private  String attStatus;

 private  int lateMin;

 private  int lvSumupId;

 private  String deptName;


public String getId(){
    return id;
}


public void setAttStatus(String attStatus){
    this.attStatus = attStatus;
}


public String getDeptName(){
    return deptName;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpName(){
    return empName;
}


public int getLateMin(){
    return lateMin;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public String getAttStatus(){
    return attStatus;
}


public void setLvSumupId(int lvSumupId){
    this.lvSumupId = lvSumupId;
}


public void setId(String id){
    this.id = id;
}


@Override
public String toString(){
    return "PresentAttendaceList [id=" + id + ", empId=" + empId + ", empName=" + empName + ", attStatus=" + attStatus + ", lateMin=" + lateMin + ", lvSumupId=" + lvSumupId + ", deptName=" + deptName + "]";
}


public int getLvSumupId(){
    return lvSumupId;
}


public void setLateMin(int lateMin){
    this.lateMin = lateMin;
}


}