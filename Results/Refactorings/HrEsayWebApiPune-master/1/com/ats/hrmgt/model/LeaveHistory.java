import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class LeaveHistory {

@Id
 private  int lvTypeId;

 private  int lvsId;

 private  String lvTitleShort;

 private  String lvTitle;

 private  float lvsAllotedLeaves;

 private  float balLeave;

 private  float aplliedLeaeve;

 private  float sactionLeave;

 private  int isFile;

 private  int maxAccumulateCarryforward;

 private  int isCarryforward;

 private  int maxCarryforward;

 private  int isInCash;

 private  String lvsName;

 private  float leaveEncashCount;


public void setBalLeave(float balLeave){
    this.balLeave = balLeave;
}


public void setMaxAccumulateCarryforward(int maxAccumulateCarryforward){
    this.maxAccumulateCarryforward = maxAccumulateCarryforward;
}


public void setLvTypeId(int lvTypeId){
    this.lvTypeId = lvTypeId;
}


public int getIsFile(){
    return isFile;
}


public float getLvsAllotedLeaves(){
    return lvsAllotedLeaves;
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


public void setLeaveEncashCount(float leaveEncashCount){
    this.leaveEncashCount = leaveEncashCount;
}


public void setIsFile(int isFile){
    this.isFile = isFile;
}


public int getLvsId(){
    return lvsId;
}


public void setAplliedLeaeve(float aplliedLeaeve){
    this.aplliedLeaeve = aplliedLeaeve;
}


public float getLeaveEncashCount(){
    return leaveEncashCount;
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
    return "LeaveHistory [lvTypeId=" + lvTypeId + ", lvsId=" + lvsId + ", lvTitleShort=" + lvTitleShort + ", lvTitle=" + lvTitle + ", lvsAllotedLeaves=" + lvsAllotedLeaves + ", balLeave=" + balLeave + ", aplliedLeaeve=" + aplliedLeaeve + ", sactionLeave=" + sactionLeave + ", isFile=" + isFile + ", maxAccumulateCarryforward=" + maxAccumulateCarryforward + ", isCarryforward=" + isCarryforward + ", maxCarryforward=" + maxCarryforward + ", isInCash=" + isInCash + ", lvsName=" + lvsName + ", leaveEncashCount=" + leaveEncashCount + "]";
}


}