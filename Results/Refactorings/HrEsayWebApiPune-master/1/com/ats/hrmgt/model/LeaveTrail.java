import javax.persistence;
@Entity
@Table(name = "leave_trail")
public class LeaveTrail {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "trail_pkey")
 private  int trailPkey;

@Column(name = "leave_id")
 private  int leaveId;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "emp_remarks")
 private  String empRemarks;

@Column(name = "leave_status")
 private  int leaveStatus;

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


public void setEmpRemarks(String empRemarks){
    this.empRemarks = empRemarks;
}


public String getExVar1(){
    return exVar1;
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


public void setTrailPkey(int trailPkey){
    this.trailPkey = trailPkey;
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


public int getLeaveStatus(){
    return leaveStatus;
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


public String getEmpRemarks(){
    return empRemarks;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public int getTrailPkey(){
    return trailPkey;
}


public int getLeaveId(){
    return leaveId;
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


public void setError(boolean error){
    this.error = error;
}


public void setLeaveStatus(int leaveStatus){
    this.leaveStatus = leaveStatus;
}


public void setLeaveId(int leaveId){
    this.leaveId = leaveId;
}


@Override
public String toString(){
    return "LeaveTrail [trailPkey=" + trailPkey + ", leaveId=" + leaveId + ", empId=" + empId + ", empRemarks=" + empRemarks + ", leaveStatus=" + leaveStatus + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", getTrailPkey()=" + getTrailPkey() + ", getLeaveId()=" + getLeaveId() + ", getEmpId()=" + getEmpId() + ", getEmpRemarks()=" + getEmpRemarks() + ", getLeaveStatus()=" + getLeaveStatus() + ", getMakerUserId()=" + getMakerUserId() + ", getMakerEnterDatetime()=" + getMakerEnterDatetime() + ", getExInt1()=" + getExInt1() + ", getExInt2()=" + getExInt2() + ", getExInt3()=" + getExInt3() + ", getExVar1()=" + getExVar1() + ", getExVar2()=" + getExVar2() + ", getExVar3()=" + getExVar3() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}


}