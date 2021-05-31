import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class DeductionAndLoanAMT {

@Id
 private  String id;

 private  int empId;

 private  float amt;

 private  String monthYear;


public String getMonthYear(){
    return monthYear;
}


public void setMonthYear(String monthYear){
    this.monthYear = monthYear;
}


public void setAmt(float amt){
    this.amt = amt;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public float getAmt(){
    return amt;
}


@Override
public String toString(){
    return "DeductionAndLoanAMT [id=" + id + ", empId=" + empId + ", amt=" + amt + ", monthYear=" + monthYear + "]";
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


}