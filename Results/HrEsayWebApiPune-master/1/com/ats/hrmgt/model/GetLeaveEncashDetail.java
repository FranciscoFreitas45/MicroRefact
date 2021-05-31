import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class GetLeaveEncashDetail {

@Id
 private  int id;

 private  int empId;

 private  int lvTypeId;

 private  int month;

 private  int year;

 private  int isFreeze;

 private  float leaveCount;

 private  float totalAmt;

 private  float perDayAmt;

 private  int exInt1;

 private  int exInt2;

 private  String exVarchar1;

 private  String exVarchar2;

 private  int yearId;

 private  int delStatus;

 private  String remark;

 private  int addedBy;

 private  String lvTitle;


public float getPerDayAmt(){
    return perDayAmt;
}


public String getExVarchar1(){
    return exVarchar1;
}


public int getExInt2(){
    return exInt2;
}


public String getExVarchar2(){
    return exVarchar2;
}


public void setLvTypeId(int lvTypeId){
    this.lvTypeId = lvTypeId;
}


public int getExInt1(){
    return exInt1;
}


public int getId(){
    return id;
}


public void setTotalAmt(float totalAmt){
    this.totalAmt = totalAmt;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setLeaveCount(float leaveCount){
    this.leaveCount = leaveCount;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getRemark(){
    return remark;
}


public void setId(int id){
    this.id = id;
}


public int getMonth(){
    return month;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public int getIsFreeze(){
    return isFreeze;
}


public int getAddedBy(){
    return addedBy;
}


public void setIsFreeze(int isFreeze){
    this.isFreeze = isFreeze;
}


public void setPerDayAmt(float perDayAmt){
    this.perDayAmt = perDayAmt;
}


public int getYearId(){
    return yearId;
}


public void setLvTitle(String lvTitle){
    this.lvTitle = lvTitle;
}


public void setMonth(int month){
    this.month = month;
}


public String getLvTitle(){
    return lvTitle;
}


public float getLeaveCount(){
    return leaveCount;
}


public void setAddedBy(int addedBy){
    this.addedBy = addedBy;
}


public void setExVarchar1(String exVarchar1){
    this.exVarchar1 = exVarchar1;
}


public void setExVarchar2(String exVarchar2){
    this.exVarchar2 = exVarchar2;
}


public float getTotalAmt(){
    return totalAmt;
}


public int getEmpId(){
    return empId;
}


public void setYear(int year){
    this.year = year;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public int getYear(){
    return year;
}


public int getDelStatus(){
    return delStatus;
}


public int getLvTypeId(){
    return lvTypeId;
}


public void setYearId(int yearId){
    this.yearId = yearId;
}


@Override
public String toString(){
    return "GetLeaveEncashDetail [id=" + id + ", empId=" + empId + ", lvTypeId=" + lvTypeId + ", month=" + month + ", year=" + year + ", isFreeze=" + isFreeze + ", leaveCount=" + leaveCount + ", totalAmt=" + totalAmt + ", perDayAmt=" + perDayAmt + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVarchar1=" + exVarchar1 + ", exVarchar2=" + exVarchar2 + ", yearId=" + yearId + ", delStatus=" + delStatus + ", remark=" + remark + ", addedBy=" + addedBy + ", lvTitle=" + lvTitle + "]";
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


}