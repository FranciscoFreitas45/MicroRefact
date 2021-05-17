import javax.persistence;
@Entity
@Table(name = "leave_summary")
public class LeaveSummary {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "lv_sumup_id")
 private  int lvSumupId;

@Column(name = "company_id")
 private  int companyId;

@Column(name = "lv_sumup_title")
 private  String lvSumupTitle;

@Column(name = "lv_sumup_title_short")
 private  String lvSumupTitleShort;

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


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public void setLvSumupTitle(String lvSumupTitle){
    this.lvSumupTitle = lvSumupTitle;
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


public void setLvSumupId(int lvSumupId){
    this.lvSumupId = lvSumupId;
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


public String getLvSumupTitle(){
    return lvSumupTitle;
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


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getIsActive(){
    return isActive;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


public void setError(boolean isError){
    this.isError = isError;
}


public void setLvSumupTitleShort(String lvSumupTitleShort){
    this.lvSumupTitleShort = lvSumupTitleShort;
}


public String getLvSumupTitleShort(){
    return lvSumupTitleShort;
}


@Override
public String toString(){
    return "LeaveSummary [lvSumupId=" + lvSumupId + ", companyId=" + companyId + ", lvSumupTitle=" + lvSumupTitle + ", lvSumupTitleShort=" + lvSumupTitleShort + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", isError=" + isError + "]";
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