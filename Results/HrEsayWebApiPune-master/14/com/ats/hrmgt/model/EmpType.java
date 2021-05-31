import javax.persistence;
@Entity
@Table(name = "m_access_role_name")
public class EmpType {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_type_id")
 private  int empTypeId;

@Column(name = "company_id")
 private  int companyId;

@Column(name = "emp_type_name")
 private  String empTypeName;

@Column(name = "emp_type_short_name")
 private  String empTypeShortName;

@Column(name = "comp_off_request_allowed")
 private  int compOffRequestAllowed;

@Column(name = "setting1")
 private  int setting1;

@Column(name = "setting2")
 private  int setting2;

@Column(name = "emp_type_remarks")
 private  String empTypeRemarks;

@Column(name = "emp_type_access")
 private  String empTypeAccess;

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


public int getSetting2(){
    return setting2;
}


public int getExInt1(){
    return exInt1;
}


public void setEmpTypeShortName(String empTypeShortName){
    this.empTypeShortName = empTypeShortName;
}


public int getCompOffRequestAllowed(){
    return compOffRequestAllowed;
}


public String getExVar1(){
    return exVar1;
}


public int getSetting1(){
    return setting1;
}


public String getEmpTypeRemarks(){
    return empTypeRemarks;
}


public boolean isError(){
    return isError;
}


public void setEmpTypeId(int empTypeId){
    this.empTypeId = empTypeId;
}


public void setEmpTypeAccess(String empTypeAccess){
    this.empTypeAccess = empTypeAccess;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public String getEmpTypeName(){
    return empTypeName;
}


public void setExInt3(int exInt3){
    this.exInt3 = exInt3;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public int getEmpTypeId(){
    return empTypeId;
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


public String getEmpTypeAccess(){
    return empTypeAccess;
}


public void setEmpTypeRemarks(String empTypeRemarks){
    this.empTypeRemarks = empTypeRemarks;
}


public void setCompOffRequestAllowed(int compOffRequestAllowed){
    this.compOffRequestAllowed = compOffRequestAllowed;
}


public int getIsActive(){
    return isActive;
}


public void setSetting2(int setting2){
    this.setting2 = setting2;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


public void setSetting1(int setting1){
    this.setting1 = setting1;
}


public void setError(boolean isError){
    this.isError = isError;
}


public void setEmpTypeName(String empTypeName){
    this.empTypeName = empTypeName;
}


@Override
public String toString(){
    return "EmpType [empTypeId=" + empTypeId + ", companyId=" + companyId + ", empTypeName=" + empTypeName + ", empTypeShortName=" + empTypeShortName + ", compOffRequestAllowed=" + compOffRequestAllowed + ", setting1=" + setting1 + ", setting2=" + setting2 + ", empTypeRemarks=" + empTypeRemarks + ", empTypeAccess=" + empTypeAccess + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", isError=" + isError + "]";
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


public String getEmpTypeShortName(){
    return empTypeShortName;
}


}