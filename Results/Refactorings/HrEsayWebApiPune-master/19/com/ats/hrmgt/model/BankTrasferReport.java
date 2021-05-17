import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class BankTrasferReport {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int empId;

 private  String name;

 private  String empTypeName;

 private  String departName;

 private  String designName;

 private  String netSalary;

 private  String accNo;

 private  int bankId;

 private  String bankName;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String getDesignName(){
    return designName;
}


public void setDepartName(String departName){
    this.departName = departName;
}


public void setBankId(int bankId){
    this.bankId = bankId;
}


public void setBankName(String bankName){
    this.bankName = bankName;
}


public void setDesignName(String designName){
    this.designName = designName;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getAccNo(){
    return accNo;
}


public void setAccNo(String accNo){
    this.accNo = accNo;
}


public int getBankId(){
    return bankId;
}


public String getDepartName(){
    return departName;
}


public void setEmpTypeName(String empTypeName){
    this.empTypeName = empTypeName;
}


public String getEmpTypeName(){
    return empTypeName;
}


public String getBankName(){
    return bankName;
}


@Override
public String toString(){
    return "BankTrasferReport [empId=" + empId + ", name=" + name + ", empTypeName=" + empTypeName + ", departName=" + departName + ", designName=" + designName + ", netSalary=" + netSalary + ", accNo=" + accNo + ", bankId=" + bankId + ", bankName=" + bankName + "]";
}


public String getNetSalary(){
    return netSalary;
}


public void setNetSalary(String netSalary){
    this.netSalary = netSalary;
}


}