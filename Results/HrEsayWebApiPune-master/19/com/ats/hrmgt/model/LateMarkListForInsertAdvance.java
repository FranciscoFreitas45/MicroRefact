import javax.persistence;
@Entity
public class LateMarkListForInsertAdvance {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_id")
 private  int empId;

@Column(name = "gr_sal")
 private  double grSal;

@Column(name = "totlate_days")
 private  float totlateDays;

@Column(name = "total_days_inmonth")
 private  int totalDaysInmonth;


public void setTotlateDays(float totlateDays){
    this.totlateDays = totlateDays;
}


public double getGrSal(){
    return grSal;
}


public void setTotalDaysInmonth(int totalDaysInmonth){
    this.totalDaysInmonth = totalDaysInmonth;
}


@Override
public String toString(){
    return "LateMarkListForInsertAdvance [empId=" + empId + ", grSal=" + grSal + ", totlateDays=" + totlateDays + ", totalDaysInmonth=" + totalDaysInmonth + "]";
}


public int getTotalDaysInmonth(){
    return totalDaysInmonth;
}


public int getEmpId(){
    return empId;
}


public void setGrSal(double grSal){
    this.grSal = grSal;
}


public float getTotlateDays(){
    return totlateDays;
}


public void setEmpId(int empId){
    this.empId = empId;
}


}