import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class GetStructureAllotment {

@Id
 private  int empId;

 private  String empCode;

 private  String empFname;

 private  String empMname;

 private  String empSname;

 private  String empDeptName;

 private  String empCatName;

 private  String lvsName;


public String getEmpMname(){
    return empMname;
}


public void setEmpDeptName(String empDeptName){
    this.empDeptName = empDeptName;
}


public void setLvsName(String lvsName){
    this.lvsName = lvsName;
}


public void setEmpSname(String empSname){
    this.empSname = empSname;
}


public int getEmpId(){
    return empId;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setEmpMname(String empMname){
    this.empMname = empMname;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setEmpCatName(String empCatName){
    this.empCatName = empCatName;
}


public String getEmpSname(){
    return empSname;
}


public String getEmpDeptName(){
    return empDeptName;
}


public String getEmpCode(){
    return empCode;
}


public void setEmpFname(String empFname){
    this.empFname = empFname;
}


@Override
public String toString(){
    return "GetStructureAllotment [empId=" + empId + ", empCode=" + empCode + ", empFname=" + empFname + ", empMname=" + empMname + ", empSname=" + empSname + ", empDeptName=" + empDeptName + ", empCatName=" + empCatName + ", lvsName=" + lvsName + "]";
}


public String getEmpFname(){
    return empFname;
}


public String getLvsName(){
    return lvsName;
}


public String getEmpCatName(){
    return empCatName;
}


}