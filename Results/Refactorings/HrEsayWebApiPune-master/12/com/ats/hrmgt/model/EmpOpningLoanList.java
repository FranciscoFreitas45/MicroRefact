import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;
@Entity
public class EmpOpningLoanList {

@Id
 private  String id;

 private  int empId;

 private  String empName;

 private  String monthYear;

 private  float opAmt;

 private  int loanCount;

@Transient
 private  List<LedgerDetailList> ledgerList;


public void setLoanCount(int loanCount){
    this.loanCount = loanCount;
}


public String getId(){
    return id;
}


public List<LedgerDetailList> getLedgerList(){
    return ledgerList;
}


public int getEmpId(){
    return empId;
}


public int getLoanCount(){
    return loanCount;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpName(){
    return empName;
}


public String getMonthYear(){
    return monthYear;
}


public void setMonthYear(String monthYear){
    this.monthYear = monthYear;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public void setLedgerList(List<LedgerDetailList> ledgerList){
    this.ledgerList = ledgerList;
}


public void setId(String id){
    this.id = id;
}


@Override
public String toString(){
    return "EmpOpningLoanList [id=" + id + ", empId=" + empId + ", empName=" + empName + ", monthYear=" + monthYear + ", opAmt=" + opAmt + ", loanCount=" + loanCount + ", ledgerList=" + ledgerList + "]";
}


public float getOpAmt(){
    return opAmt;
}


public void setOpAmt(float opAmt){
    this.opAmt = opAmt;
}


}