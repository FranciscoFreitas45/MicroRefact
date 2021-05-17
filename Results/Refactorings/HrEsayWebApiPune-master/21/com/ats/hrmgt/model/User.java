import javax.persistence;
@Entity
@Table(name = "m_user")
public class User {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "user_id")
 private  int user_id;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "emp_type_id")
 private  int empTypeId;

@Column(name = "loc_id")
 private  String locId;

@Column(name = "user_name")
 private  String userName;

@Column(name = "user_pwd")
 private  String userPwd;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "is_active")
 private  int isActive;

@Column(name = "maker_user_id")
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


public String getLocId(){
    return locId;
}


public String getExVar1(){
    return exVar1;
}


public void setUserPwd(String userPwd){
    this.userPwd = userPwd;
}


public int getUser_id(){
    return user_id;
}


public boolean isError(){
    return error;
}


public void setEmpTypeId(int empTypeId){
    this.empTypeId = empTypeId;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setUserName(String userName){
    this.userName = userName;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public String getUserName(){
    return userName;
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


public void setLocId(String locId){
    this.locId = locId;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public int getIsActive(){
    return isActive;
}


public void setUser_id(int user_id){
    this.user_id = user_id;
}


public int getDelStatus(){
    return delStatus;
}


public void setError(boolean error){
    this.error = error;
}


@Override
public String toString(){
    return "User [user_id=" + user_id + ", empId=" + empId + ", empTypeId=" + empTypeId + ", locId=" + locId + ", userName=" + userName + ", userPwd=" + userPwd + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", error=" + error + "]";
}


public String getUserPwd(){
    return userPwd;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIsActive(int isActive){
    this.isActive = isActive;
}


}