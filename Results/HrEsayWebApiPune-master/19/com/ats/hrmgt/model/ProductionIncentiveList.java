import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
@Entity
public class ProductionIncentiveList {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  String id;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "att_date")
 private  Date attDate;

@Column(name = "hrs")
 private  int hrs;

@Column(name = "tot_othr")
 private  int totOthr;

@Column(name = "show_hrs")
 private  String showHrs;

@Column(name = "att_status")
 private  String attStatus;

@Transient
 private  double amt;


public int getTotOthr(){
    return totOthr;
}


public void setAmt(double amt){
    this.amt = amt;
}


public void setHrs(int hrs){
    this.hrs = hrs;
}


public void setTotOthr(int totOthr){
    this.totOthr = totOthr;
}


@JsonFormat(locale = "ENGLISH", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAttDate(){
    return attDate;
}


public String getId(){
    return id;
}


public void setAttStatus(String attStatus){
    this.attStatus = attStatus;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getShowHrs(){
    return showHrs;
}


public String getAttStatus(){
    return attStatus;
}


public void setId(String id){
    this.id = id;
}


public double getAmt(){
    return amt;
}


@Override
public String toString(){
    return "ProductionIncentiveList [id=" + id + ", empId=" + empId + ", attDate=" + attDate + ", hrs=" + hrs + ", totOthr=" + totOthr + ", showHrs=" + showHrs + ", attStatus=" + attStatus + ", amt=" + amt + "]";
}


public int getHrs(){
    return hrs;
}


public void setShowHrs(String showHrs){
    this.showHrs = showHrs;
}


public void setAttDate(Date attDate){
    this.attDate = attDate;
}


}