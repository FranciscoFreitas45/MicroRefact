import javax.persistence;
@Entity
public class EmpSalType {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_id")
 private  int empId;

@Column(name = "sal_basis")
 private  String salBasis;

@Column(name = "daily_hr")
 private  String dailyHr;

@Column(name = "monthly_hr_target")
 private  String monthlyHrTarget;

@Column(name = "monthly_minimum_target")
 private  String monthlyMinimumTarget;

@Column(name = "monthly_ot_hr")
 private  String monthlyOtHr;


public void setMonthlyMinimumTarget(String monthlyMinimumTarget){
    this.monthlyMinimumTarget = monthlyMinimumTarget;
}


public String getMonthlyOtHr(){
    return monthlyOtHr;
}


public String getMonthlyMinimumTarget(){
    return monthlyMinimumTarget;
}


public int getEmpId(){
    return empId;
}


public String getMonthlyHrTarget(){
    return monthlyHrTarget;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getSalBasis(){
    return salBasis;
}


public void setDailyHr(String dailyHr){
    this.dailyHr = dailyHr;
}


public String getDailyHr(){
    return dailyHr;
}


public void setSalBasis(String salBasis){
    this.salBasis = salBasis;
}


public void setMonthlyHrTarget(String monthlyHrTarget){
    this.monthlyHrTarget = monthlyHrTarget;
}


@Override
public String toString(){
    return "EmpSalType [empId=" + empId + ", salBasis=" + salBasis + ", dailyHr=" + dailyHr + ", monthlyHrTarget=" + monthlyHrTarget + ", monthlyMinimumTarget=" + monthlyMinimumTarget + ", monthlyOtHr=" + monthlyOtHr + "]";
}


public void setMonthlyOtHr(String monthlyOtHr){
    this.monthlyOtHr = monthlyOtHr;
}


}