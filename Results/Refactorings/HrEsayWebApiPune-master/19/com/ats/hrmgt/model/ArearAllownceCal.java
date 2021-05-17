import javax.persistence;
@Entity
@Table(name = "t_arear_detail")
public class ArearAllownceCal {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_sal_allowance_id")
 private  int empSalAllowanceId;

@Column(name = "salary_calc_id")
 private  int salaryCalcId;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "allowance_id")
 private  int allowanceId;

@Column(name = "short_name")
 private  String shortName;

@Column(name = "allowance_value")
 private  double allowanceValue;

@Column(name = "allowance_value_cal")
 private  double allowanceValueCal;

@Column(name = "maker_enter_datetime")
 private  String makerEnterDatetime;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_var1")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public int getExInt2(){
    return exInt2;
}


public void setEmpSalAllowanceId(int empSalAllowanceId){
    this.empSalAllowanceId = empSalAllowanceId;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public double getAllowanceValue(){
    return allowanceValue;
}


public void setAllowanceValueCal(double allowanceValueCal){
    this.allowanceValueCal = allowanceValueCal;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public double getAllowanceValueCal(){
    return allowanceValueCal;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public int getAllowanceId(){
    return allowanceId;
}


public void setAllowanceValue(double allowanceValue){
    this.allowanceValue = allowanceValue;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setSalaryCalcId(int salaryCalcId){
    this.salaryCalcId = salaryCalcId;
}


public int getEmpId(){
    return empId;
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


public int getSalaryCalcId(){
    return salaryCalcId;
}


public String getShortName(){
    return shortName;
}


public void setShortName(String shortName){
    this.shortName = shortName;
}


public void setAllowanceId(int allowanceId){
    this.allowanceId = allowanceId;
}


@Override
public String toString(){
    return "SalAllownceCal [empSalAllowanceId=" + empSalAllowanceId + ", salaryCalcId=" + salaryCalcId + ", empId=" + empId + ", allowanceId=" + allowanceId + ", shortName=" + shortName + ", allowanceValue=" + allowanceValue + ", allowanceValueCal=" + allowanceValueCal + ", makerEnterDatetime=" + makerEnterDatetime + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


}