import javax.persistence;
@Entity
@Table(name = "emp_sal_allowance")
public class EmpSalAllowance {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int empSalAllowanceId;

 private  int empId;

 private  int allowanceId;

 private  Double allowanceValue;

 private  String makerEnterDatetime;

 private  int delStatus;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public int getExInt2(){
    return exInt2;
}


public void setAllowanceValue(Double allowanceValue){
    this.allowanceValue = allowanceValue;
}


public void setEmpSalAllowanceId(int empSalAllowanceId){
    this.empSalAllowanceId = empSalAllowanceId;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getEmpId(){
    return empId;
}


public Double getAllowanceValue(){
    return allowanceValue;
}


public int getEmpSalAllowanceId(){
    return empSalAllowanceId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public int getDelStatus(){
    return delStatus;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setAllowanceId(int allowanceId){
    this.allowanceId = allowanceId;
}


@Override
public String toString(){
    return "EmpSalAllowance [empSalAllowanceId=" + empSalAllowanceId + ", empId=" + empId + ", allowanceId=" + allowanceId + ", allowanceValue=" + allowanceValue + ", makerEnterDatetime=" + makerEnterDatetime + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public int getAllowanceId(){
    return allowanceId;
}


}