import javax.persistence;
@Entity
@Table(name = "leave_type")
public class LeaveType {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "lv_type_id")
 private  int lvTypeId;

@Column(name = "company_id")
 private  int companyId;

@Column(name = "lv_title")
 private  String lvTitle;

@Column(name = "lv_title_short")
 private  String lvTitleShort;

@Column(name = "is_file")
 private  int isFile;

@Column(name = "lv_sumup_id")
 private  int lvSumupId;

@Column(name = "lv_working_hrs")
 private  int lvWorkingHrs;

@Column(name = "lv_color")
 private  String lvColor;

@Column(name = "is_structured")
 private  int isStructured;

@Column(name = "lv_remarks")
 private  String lvRmarks;

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
 private  boolean error;


public void setLvWorkingHrs(int lvWorkingHrs){
    this.lvWorkingHrs = lvWorkingHrs;
}


public String getExVar2(){
    return exVar2;
}


public String getExVar3(){
    return exVar3;
}


public int getExInt2(){
    return exInt2;
}


public int getLvWorkingHrs(){
    return lvWorkingHrs;
}


public int getExInt3(){
    return exInt3;
}


public void setLvTypeId(int lvTypeId){
    this.lvTypeId = lvTypeId;
}


public int getIsFile(){
    return isFile;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public void setLvRmarks(String lvRmarks){
    this.lvRmarks = lvRmarks;
}


public boolean isError(){
    return error;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setLvSumupId(int lvSumupId){
    this.lvSumupId = lvSumupId;
}


public String getLvTitleShort(){
    return lvTitleShort;
}


public void setExInt3(int exInt3){
    this.exInt3 = exInt3;
}


public int getLvSumupId(){
    return lvSumupId;
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


public void setLvTitle(String lvTitle){
    this.lvTitle = lvTitle;
}


public String getLvTitle(){
    return lvTitle;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getMakerUserId(){
    return makerUserId;
}


public void setIsFile(int isFile){
    this.isFile = isFile;
}


public String getLvRmarks(){
    return lvRmarks;
}


public void setLvColor(String lvColor){
    this.lvColor = lvColor;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public String getLvColor(){
    return lvColor;
}


public int getIsActive(){
    return isActive;
}


public void setIsStructured(int isStructured){
    this.isStructured = isStructured;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


public void setError(boolean error){
    this.error = error;
}


public void setLvTitleShort(String lvTitleShort){
    this.lvTitleShort = lvTitleShort;
}


public int getIsStructured(){
    return isStructured;
}


public int getLvTypeId(){
    return lvTypeId;
}


@Override
public String toString(){
    return "LeaveType [lvTypeId=" + lvTypeId + ", companyId=" + companyId + ", lvTitle=" + lvTitle + ", lvTitleShort=" + lvTitleShort + ", isFile=" + isFile + ", lvSumupId=" + lvSumupId + ", lvWorkingHrs=" + lvWorkingHrs + ", lvColor=" + lvColor + ", isStructured=" + isStructured + ", lvRmarks=" + lvRmarks + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", error=" + error + "]";
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIsActive(int isActive){
    this.isActive = isActive;
}


}