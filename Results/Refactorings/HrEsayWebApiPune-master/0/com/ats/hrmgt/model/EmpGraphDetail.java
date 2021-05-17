import javax.persistence;
@Entity
public class EmpGraphDetail {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "month")
 private  String month;

@Column(name = "late_min")
 private  String lateMin;

@Column(name = "late_mark")
 private  String lateMark;

@Column(name = "leave_count")
 private  int leaveCount;

@Column(name = "lwp")
 private  int lwp;

@Column(name = "ab")
 private  String ab;


public void setMonth(String month){
    this.month = month;
}


public void setLwp(int lwp){
    this.lwp = lwp;
}


public int getLeaveCount(){
    return leaveCount;
}


public void setLateMark(String lateMark){
    this.lateMark = lateMark;
}


public int getLwp(){
    return lwp;
}


public void setAb(String ab){
    this.ab = ab;
}


public int getId(){
    return id;
}


public String getAb(){
    return ab;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getLateMin(){
    return lateMin;
}


public String getLateMark(){
    return lateMark;
}


public void setLeaveCount(int leaveCount){
    this.leaveCount = leaveCount;
}


public void setId(int id){
    this.id = id;
}


@Override
public String toString(){
    return "EmpGraphDetail [id=" + id + ", empId=" + empId + ", month=" + month + ", lateMin=" + lateMin + ", lateMark=" + lateMark + ", leaveCount=" + leaveCount + ", lwp=" + lwp + ", ab=" + ab + "]";
}


public String getMonth(){
    return month;
}


public void setLateMin(String lateMin){
    this.lateMin = lateMin;
}


}