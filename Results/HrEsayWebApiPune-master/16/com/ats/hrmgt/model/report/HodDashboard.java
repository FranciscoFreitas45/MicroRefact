import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class HodDashboard {

@Id
 private  int empId;

 private  String firstName;

 private  String surname;

 private  String empCode;

 private  float presentDays;

 private  float weekOffCovered;

 private  float abDays;

 private  float lastMonthPendWoff;

 private  float otLastMonth;

 private  float workingDays;


public void setPresentDays(float presentDays){
    this.presentDays = presentDays;
}


public float getAbDays(){
    return abDays;
}


public int getEmpId(){
    return empId;
}


public void setAbDays(float abDays){
    this.abDays = abDays;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setLastMonthPendWoff(float lastMonthPendWoff){
    this.lastMonthPendWoff = lastMonthPendWoff;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setWorkingDays(float workingDays){
    this.workingDays = workingDays;
}


public float getPresentDays(){
    return presentDays;
}


public float getWorkingDays(){
    return workingDays;
}


public String getEmpCode(){
    return empCode;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public void setSurname(String surname){
    this.surname = surname;
}


public float getOtLastMonth(){
    return otLastMonth;
}


public void setWeekOffCovered(float weekOffCovered){
    this.weekOffCovered = weekOffCovered;
}


public float getLastMonthPendWoff(){
    return lastMonthPendWoff;
}


@Override
public String toString(){
    return "HodDashboard [empId=" + empId + ", firstName=" + firstName + ", surname=" + surname + ", empCode=" + empCode + ", presentDays=" + presentDays + ", weekOffCovered=" + weekOffCovered + ", abDays=" + abDays + ", lastMonthPendWoff=" + lastMonthPendWoff + ", otLastMonth=" + otLastMonth + ", workingDays=" + workingDays + "]";
}


public void setOtLastMonth(float otLastMonth){
    this.otLastMonth = otLastMonth;
}


public String getFirstName(){
    return firstName;
}


public String getSurname(){
    return surname;
}


public float getWeekOffCovered(){
    return weekOffCovered;
}


}