import javax.persistence;
@Entity
public class EmpBasicAllownceForLeaveInCash {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_id")
 private  int empId;

 private  double basic;

 private  double allowanceValue;


public void setBasic(double basic){
    this.basic = basic;
}


public void setAllowanceValue(double allowanceValue){
    this.allowanceValue = allowanceValue;
}


public double getBasic(){
    return basic;
}


@Override
public String toString(){
    return "EmpBasicAllownceForLeaveInCash [empId=" + empId + ", basic=" + basic + ", allowanceValue=" + allowanceValue + "]";
}


public int getEmpId(){
    return empId;
}


public double getAllowanceValue(){
    return allowanceValue;
}


public void setEmpId(int empId){
    this.empId = empId;
}


}