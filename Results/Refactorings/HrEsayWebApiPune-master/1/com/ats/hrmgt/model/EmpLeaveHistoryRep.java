import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class EmpLeaveHistoryRep {

@Id
 private  String recId;

 private  int empId;

 private  String empName;

 private  int lvTypeId;

 private  String lvTitleShort;

 private  String lvTitle;

 private  float lvsAllotedLeaves;

 private  int lvsId;

 private  float balLeave;

 private  float sactionLeave;

 private  float aplliedLeaeve;

 private  float leaveEncashCount;


public void setBalLeave(float balLeave){
    this.balLeave = balLeave;
}


public void setLvTypeId(int lvTypeId){
    this.lvTypeId = lvTypeId;
}


public String getRecId(){
    return recId;
}


public float getLvsAllotedLeaves(){
    return lvsAllotedLeaves;
}


public void setSactionLeave(float sactionLeave){
    this.sactionLeave = sactionLeave;
}


public String getEmpName(){
    return empName;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public float getBalLeave(){
    return balLeave;
}


public float getAplliedLeaeve(){
    return aplliedLeaeve;
}


public float getSactionLeave(){
    return sactionLeave;
}


public void setLvsAllotedLeaves(float lvsAllotedLeaves){
    this.lvsAllotedLeaves = lvsAllotedLeaves;
}


public String getLvTitleShort(){
    return lvTitleShort;
}


public void setLvTitle(String lvTitle){
    this.lvTitle = lvTitle;
}


public String getLvTitle(){
    return lvTitle;
}


public void setLeaveEncashCount(float leaveEncashCount){
    this.leaveEncashCount = leaveEncashCount;
}


public void setRecId(String recId){
    this.recId = recId;
}


public int getLvsId(){
    return lvsId;
}


public void setAplliedLeaeve(float aplliedLeaeve){
    this.aplliedLeaeve = aplliedLeaeve;
}


public int getEmpId(){
    return empId;
}


public float getLeaveEncashCount(){
    return leaveEncashCount;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setLvTitleShort(String lvTitleShort){
    this.lvTitleShort = lvTitleShort;
}


public void setLvsId(int lvsId){
    this.lvsId = lvsId;
}


public int getLvTypeId(){
    return lvTypeId;
}


@Override
public String toString(){
    return "EmpLeaveHistoryRep [recId=" + recId + ", empId=" + empId + ", empName=" + empName + ", lvTypeId=" + lvTypeId + ", lvTitleShort=" + lvTitleShort + ", lvTitle=" + lvTitle + ", lvsAllotedLeaves=" + lvsAllotedLeaves + ", lvsId=" + lvsId + ", balLeave=" + balLeave + ", sactionLeave=" + sactionLeave + ", aplliedLeaeve=" + aplliedLeaeve + ", leaveEncashCount=" + leaveEncashCount + "]";
}


}