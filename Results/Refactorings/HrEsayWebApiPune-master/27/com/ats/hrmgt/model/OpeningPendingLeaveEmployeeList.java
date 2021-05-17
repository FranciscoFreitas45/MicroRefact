import javax.persistence;
@Entity
public class OpeningPendingLeaveEmployeeList {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  String id;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "emp_code")
 private  String empCode;

@Column(name = "emp_name")
 private  String empName;

@Column(name = "lvs_id")
 private  int lvsId;

@Column(name = "lv_title")
 private  String lvTitle;

@Column(name = "lv_type_id")
 private  int lvTypeId;

@Column(name = "lvs_alloted_leaves")
 private  float lvs_alloted_leaves;

@Column(name = "leave_id")
 private  int leaveId;

@Column(name = "leave_num_days")
 private  float leaveNumDays;

@Column(name = "op_bal")
 private  float opBal;

@Column(name = "lvbal_id")
 private  int lvbalId;


public void setLeaveNumDays(float leaveNumDays){
    this.leaveNumDays = leaveNumDays;
}


public void setLvTypeId(int lvTypeId){
    this.lvTypeId = lvTypeId;
}


public float getLvs_alloted_leaves(){
    return lvs_alloted_leaves;
}


public String getId(){
    return id;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getEmpName(){
    return empName;
}


public void setOpBal(float opBal){
    this.opBal = opBal;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public int getLvbalId(){
    return lvbalId;
}


public void setId(String id){
    this.id = id;
}


public float getOpBal(){
    return opBal;
}


public void setLvTitle(String lvTitle){
    this.lvTitle = lvTitle;
}


public String getLvTitle(){
    return lvTitle;
}


public int getLvsId(){
    return lvsId;
}


public int getLeaveId(){
    return leaveId;
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


public void setLvbalId(int lvbalId){
    this.lvbalId = lvbalId;
}


public void setLvsId(int lvsId){
    this.lvsId = lvsId;
}


public void setLeaveId(int leaveId){
    this.leaveId = leaveId;
}


public int getLvTypeId(){
    return lvTypeId;
}


@Override
public String toString(){
    return "OpeningPendingLeaveEmployeeList [id=" + id + ", empId=" + empId + ", empCode=" + empCode + ", empName=" + empName + ", lvsId=" + lvsId + ", lvTitle=" + lvTitle + ", lvTypeId=" + lvTypeId + ", lvs_alloted_leaves=" + lvs_alloted_leaves + ", leaveId=" + leaveId + ", leaveNumDays=" + leaveNumDays + ", opBal=" + opBal + ", lvbalId=" + lvbalId + "]";
}


public void setLvs_alloted_leaves(float lvs_alloted_leaves){
    this.lvs_alloted_leaves = lvs_alloted_leaves;
}


public float getLeaveNumDays(){
    return leaveNumDays;
}


}