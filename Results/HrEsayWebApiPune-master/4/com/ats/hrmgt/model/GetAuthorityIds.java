import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class GetAuthorityIds {

@Id
 private  int empId;

 private  int iniAuthEmpId;

 private  int finAuthEmpId;

 private  String repToEmpIds;


public void setRepToEmpIds(String repToEmpIds){
    this.repToEmpIds = repToEmpIds;
}


public void setFinAuthEmpId(int finAuthEmpId){
    this.finAuthEmpId = finAuthEmpId;
}


public String getRepToEmpIds(){
    return repToEmpIds;
}


public void setIniAuthEmpId(int iniAuthEmpId){
    this.iniAuthEmpId = iniAuthEmpId;
}


public int getIniAuthEmpId(){
    return iniAuthEmpId;
}


@Override
public String toString(){
    return "GetAuthorityIds [empId=" + empId + ", iniAuthEmpId=" + iniAuthEmpId + ", finAuthEmpId=" + finAuthEmpId + ", getEmpId()=" + getEmpId() + ", getIniAuthEmpId()=" + getIniAuthEmpId() + ", getFinAuthEmpId()=" + getFinAuthEmpId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}


public int getFinAuthEmpId(){
    return finAuthEmpId;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


}