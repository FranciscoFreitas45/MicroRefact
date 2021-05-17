import javax.persistence;
@Entity
@Table(name = "m_location")
public class Location {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "loc_id")
 private  int locId;

@Column(name = "comp_id")
 private  int compId;

@Column(name = "loc_name")
 private  String locName;

@Column(name = "loc_name_short")
 private  String locNameShort;

@Column(name = "loc_short_address")
 private  String locShortAddress;

@Column(name = "loc_hr_contact_person")
 private  String locHrContactPerson;

@Column(name = "loc_hr_contact_number")
 private  String locHrContactNumber;

@Column(name = "loc_hr_contact_email")
 private  String locHrContactEmail;

@Column(name = "loc_remarks")
 private  String locRemarks;

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


public String getLocNameShort(){
    return locNameShort;
}


public String getLocName(){
    return locName;
}


public int getExInt1(){
    return exInt1;
}


public int getLocId(){
    return locId;
}


public String getLocHrContactEmail(){
    return locHrContactEmail;
}


public String getExVar1(){
    return exVar1;
}


public void setCompId(int compId){
    this.compId = compId;
}


public void setLocName(String locName){
    this.locName = locName;
}


public String getLocRemarks(){
    return locRemarks;
}


public void setLocHrContactNumber(String locHrContactNumber){
    this.locHrContactNumber = locHrContactNumber;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setLocHrContactPerson(String locHrContactPerson){
    this.locHrContactPerson = locHrContactPerson;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public int getCompId(){
    return compId;
}


public String getLocHrContactNumber(){
    return locHrContactNumber;
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


public void setLocNameShort(String locNameShort){
    this.locNameShort = locNameShort;
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


public void setLocId(int locId){
    this.locId = locId;
}


public void setLocHrContactEmail(String locHrContactEmail){
    this.locHrContactEmail = locHrContactEmail;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public String getLocHrContactPerson(){
    return locHrContactPerson;
}


public int getIsActive(){
    return isActive;
}


public String getLocShortAddress(){
    return locShortAddress;
}


public void setLocRemarks(String locRemarks){
    this.locRemarks = locRemarks;
}


public int getDelStatus(){
    return delStatus;
}


@Override
public String toString(){
    return "Location [locId=" + locId + ", compId=" + compId + ", locName=" + locName + ", locNameShort=" + locNameShort + ", locShortAddress=" + locShortAddress + ", locHrContactPerson=" + locHrContactPerson + ", locHrContactNumber=" + locHrContactNumber + ", locHrContactEmail=" + locHrContactEmail + ", locRemarks=" + locRemarks + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
}


public void setLocShortAddress(String locShortAddress){
    this.locShortAddress = locShortAddress;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIsActive(int isActive){
    this.isActive = isActive;
}


}