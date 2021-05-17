import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class LeaveHistoryDetailForCarry {

@Id
 private  String id;

 private  int empId;

 private  int lvTypeId;

 private  int lvsId;

 private  String lvTitleShort;

 private  String lvTitle;

 private  float lvsAllotedLeaves;

 private  float balLeave;

 private  float aplliedLeaeve;

 private  float sactionLeave;

 private  int maxAccumulateCarryforward;

 private  int isCarryforward;

 private  int maxCarryforward;

 private  int isInCash;

 private  String lvsName;


public void setBalLeave(float balLeave){
    this.balLeave = balLeave;
}


public void setMaxAccumulateCarryforward(int maxAccumulateCarryforward){
    this.maxAccumulateCarryforward = maxAccumulateCarryforward;
}


public void setLvTypeId(int lvTypeId){
    this.lvTypeId = lvTypeId;
}


public float getLvsAllotedLeaves(){
    return lvsAllotedLeaves;
}


public String getId(){
    return id;
}


public void setSactionLeave(float sactionLeave){
    this.sactionLeave = sactionLeave;
}


public void setIsCarryforward(int isCarryforward){
    this.isCarryforward = isCarryforward;
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


public void setId(String id){
    this.id = id;
}


public String getLvTitleShort(){
    return lvTitleShort;
}


public int getMaxAccumulateCarryforward(){
    return maxAccumulateCarryforward;
}


public int getIsInCash(){
    return isInCash;
}


public int getIsCarryforward(){
    return isCarryforward;
}


public String getLvsName(){
    return lvsName;
}


public void setIsInCash(int isInCash){
    this.isInCash = isInCash;
}


public void setLvTitle(String lvTitle){
    this.lvTitle = lvTitle;
}


public String getLvTitle(){
    return lvTitle;
}


public void setLvsName(String lvsName){
    this.lvsName = lvsName;
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


public void setEmpId(int empId){
    this.empId = empId;
}


public int getMaxCarryforward(){
    return maxCarryforward;
}


public void setMaxCarryforward(int maxCarryforward){
    this.maxCarryforward = maxCarryforward;
}


public void setLvsId(int lvsId){
    this.lvsId = lvsId;
}


public void setLvTitleShort(String lvTitleShort){
    this.lvTitleShort = lvTitleShort;
}


public int getLvTypeId(){
    return lvTypeId;
}


@Override
public String toString(){
    return "LeaveHistoryDetailForCarry [id=" + id + ", empId=" + empId + ", lvTypeId=" + lvTypeId + ", lvsId=" + lvsId + ", lvTitleShort=" + lvTitleShort + ", lvTitle=" + lvTitle + ", lvsAllotedLeaves=" + lvsAllotedLeaves + ", balLeave=" + balLeave + ", aplliedLeaeve=" + aplliedLeaeve + ", sactionLeave=" + sactionLeave + ", maxAccumulateCarryforward=" + maxAccumulateCarryforward + ", isCarryforward=" + isCarryforward + ", maxCarryforward=" + maxCarryforward + ", isInCash=" + isInCash + ", lvsName=" + lvsName + "]";
}


}