import javax.persistence;
@Entity
@Table(name = "m_holiday")
public class Holiday {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "holiday_id")
 private  int holidayId;

@Column(name = "cal_yr_id")
 private  int calYrId;

@Column(name = "company_id")
 private  int companyId;

@Column(name = "loc_id")
 private  String locId;

@Column(name = "holiday_fromdt")
 private  String holidayFromdt;

@Column(name = "holiday_todt")
 private  String holidayTodt;

@Column(name = "holiday_remark")
 private  String holidayRemark;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "is_active")
 private  int isActive;

@Column(name = "maker_user_id ")
 private  int makerUserId;

@Column(name = "maker_enter_datetime")
 private  String makerEnterDatetime;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_int3")
 private  int exInt3;

@Column(name = "ex_var1")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;

@Column(name = "ex_var3")
 private  String exVar3;

@Transient
 private  boolean isError;


public String getExVar2(){
    return exVar2;
}


public String getExVar3(){
    return exVar3;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt3(){
    return exInt3;
}


public int getHolidayId(){
    return holidayId;
}


public int getExInt1(){
    return exInt1;
}


public String getLocId(){
    return locId;
}


public String getExVar1(){
    return exVar1;
}


public String getHolidayRemark(){
    return holidayRemark;
}


public boolean isError(){
    return isError;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setHolidayRemark(String holidayRemark){
    this.holidayRemark = holidayRemark;
}


public String getHolidayTodt(){
    return holidayTodt;
}


public void setExInt3(int exInt3){
    this.exInt3 = exInt3;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getMakerUserId(){
    return makerUserId;
}


public void setLocId(String locId){
    this.locId = locId;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public void setCalYrId(int calYrId){
    this.calYrId = calYrId;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setHolidayFromdt(String holidayFromdt){
    this.holidayFromdt = holidayFromdt;
}


public int getIsActive(){
    return isActive;
}


public int getCalYrId(){
    return calYrId;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


public String getHolidayFromdt(){
    return holidayFromdt;
}


public void setError(boolean isError){
    this.isError = isError;
}


@Override
public String toString(){
    return "Holiday [holidayId=" + holidayId + ", calYrId=" + calYrId + ", companyId=" + companyId + ", locId=" + locId + ", holidayFromdt=" + holidayFromdt + ", holidayTodt=" + holidayTodt + ", holidayRemark=" + holidayRemark + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", isError=" + isError + "]";
}


public void setHolidayId(int holidayId){
    this.holidayId = holidayId;
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public void setHolidayTodt(String holidayTodt){
    this.holidayTodt = holidayTodt;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIsActive(int isActive){
    this.isActive = isActive;
}


}