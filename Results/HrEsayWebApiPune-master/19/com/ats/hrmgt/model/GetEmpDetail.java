import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class GetEmpDetail {

@Id
 private  int empId;

 private  String deptName;

 private  String empDesgn;

 private  String locName;

 private  String empTypeName;


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public String getEmpDesgn(){
    return empDesgn;
}


public String getLocName(){
    return locName;
}


public void setLocName(String locName){
    this.locName = locName;
}


public void setEmpTypeName(String empTypeName){
    this.empTypeName = empTypeName;
}


public String getEmpTypeName(){
    return empTypeName;
}


@Override
public String toString(){
    return "GetEmpDetail [empId=" + empId + ", deptName=" + deptName + ", empDesgn=" + empDesgn + ", locName=" + locName + ", empTypeName=" + empTypeName + "]";
}


public int getEmpId(){
    return empId;
}


public String getDeptName(){
    return deptName;
}


public void setEmpDesgn(String empDesgn){
    this.empDesgn = empDesgn;
}


public void setEmpId(int empId){
    this.empId = empId;
}


}