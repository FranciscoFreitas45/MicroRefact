import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class GetPfStatementSummary {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int id;

 private  int empId;

 private  String empCode;

 private  String epfWages;

 private  String epfWagesEmployer;

 private  String epsWages;

 private  String employerEps;

 private  String employeePf;

 private  String employerPf;

 private  String empName;

 private  String pfNo;

 private  String uan;


public void setEpfWagesEmployer(String epfWagesEmployer){
    this.epfWagesEmployer = epfWagesEmployer;
}


public String getEmployerPf(){
    return employerPf;
}


public int getId(){
    return id;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getPfNo(){
    return pfNo;
}


public String getEmpName(){
    return empName;
}


public String getEpfWagesEmployer(){
    return epfWagesEmployer;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public String getEpfWages(){
    return epfWages;
}


public void setEmployerEps(String employerEps){
    this.employerEps = employerEps;
}


public void setPfNo(String pfNo){
    this.pfNo = pfNo;
}


public void setId(int id){
    this.id = id;
}


public void setEpfWages(String epfWages){
    this.epfWages = epfWages;
}


public void setEpsWages(String epsWages){
    this.epsWages = epsWages;
}


public String getEmployerEps(){
    return employerEps;
}


public void setUan(String uan){
    this.uan = uan;
}


public String getUan(){
    return uan;
}


public String getEpsWages(){
    return epsWages;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setEmployeePf(String employeePf){
    this.employeePf = employeePf;
}


public String getEmpCode(){
    return empCode;
}


public void setEmployerPf(String employerPf){
    this.employerPf = employerPf;
}


@Override
public String toString(){
    return "GetPfStatementSummary [id=" + id + ", empId=" + empId + ", empCode=" + empCode + ", epfWages=" + epfWages + ", epfWagesEmployer=" + epfWagesEmployer + ", epsWages=" + epsWages + ", employerEps=" + employerEps + ", employeePf=" + employeePf + ", employerPf=" + employerPf + ", empName=" + empName + ", pfNo=" + pfNo + ", uan=" + uan + "]";
}


public String getEmployeePf(){
    return employeePf;
}


}