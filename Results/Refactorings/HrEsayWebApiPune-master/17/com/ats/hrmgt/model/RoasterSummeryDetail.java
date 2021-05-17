import javax.persistence;
@Entity
public class RoasterSummeryDetail {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_id")
 private  int empId;

@Column(name = "emp_code")
 private  String empCode;

@Column(name = "first_name")
 private  String firstName;

@Column(name = "middle_name")
 private  String middleName;

@Column(name = "surname")
 private  String surname;

@Column(name = "off_day_count ")
 private  int offDayCount;

@Column(name = "night_count ")
 private  int nightCount;

@Column(name = "ff_count")
 private  int ffCount;

@Column(name = "late_mark")
 private  int lateMark;

@Column(name = "late_min")
 private  int lateMin;

@Column(name = "km")
 private  int km;

@Column(name = "incentive ")
 private  float incentive;


public void setFfCount(int ffCount){
    this.ffCount = ffCount;
}


public int getFfCount(){
    return ffCount;
}


public int getKm(){
    return km;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getMiddleName(){
    return middleName;
}


public int getLateMin(){
    return lateMin;
}


public float getIncentive(){
    return incentive;
}


public void setIncentive(float incentive){
    this.incentive = incentive;
}


public void setSurname(String surname){
    this.surname = surname;
}


public int getNightCount(){
    return nightCount;
}


public void setMiddleName(String middleName){
    this.middleName = middleName;
}


public int getOffDayCount(){
    return offDayCount;
}


public void setKm(int km){
    this.km = km;
}


public void setNightCount(int nightCount){
    this.nightCount = nightCount;
}


public void setLateMark(int lateMark){
    this.lateMark = lateMark;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpCode(){
    return empCode;
}


public int getLateMark(){
    return lateMark;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public void setOffDayCount(int offDayCount){
    this.offDayCount = offDayCount;
}


@Override
public String toString(){
    return "RoasterSummeryDetail [empId=" + empId + ", empCode=" + empCode + ", firstName=" + firstName + ", middleName=" + middleName + ", surname=" + surname + ", offDayCount=" + offDayCount + ", nightCount=" + nightCount + ", ffCount=" + ffCount + ", lateMark=" + lateMark + ", lateMin=" + lateMin + ", km=" + km + ", incentive=" + incentive + "]";
}


public String getFirstName(){
    return firstName;
}


public void setLateMin(int lateMin){
    this.lateMin = lateMin;
}


public String getSurname(){
    return surname;
}


}