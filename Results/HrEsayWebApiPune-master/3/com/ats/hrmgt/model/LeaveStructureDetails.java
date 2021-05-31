import javax.persistence;
@Entity
@Table(name = "leave_structure_details")
public class LeaveStructureDetails {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "lvs_details_id")
 private  int lvsDetailsId;

@Column(name = "lvs_id")
 private  int lvsId;

@Column(name = "lv_type_id")
 private  int lvTypeId;

@Column(name = "lvs_alloted_leaves")
 private  int lvsAllotedLeaves;

@Column(name = "min_no_days")
 private  int minNoDays;

@Column(name = "max_no_days")
 private  int maxNoDays;

@Column(name = "is_carryforward")
 private  int isCarryforward;

@Column(name = "max_carryforward")
 private  int maxCarryforward;

@Column(name = "max_accumulate_carryforward")
 private  int maxAccumulateCarryforward;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "is_active")
 private  int isActive;

@Column(name = "maker_user_id ")
 private  int makerUserId;

@Column(name = "maker_datetime")
 private  String makerDatetime;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_var1")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public void setMakerDatetime(String makerDatetime){
    this.makerDatetime = makerDatetime;
}


public int getExInt2(){
    return exInt2;
}


public void setMaxNoDays(int maxNoDays){
    this.maxNoDays = maxNoDays;
}


public void setMaxAccumulateCarryforward(int maxAccumulateCarryforward){
    this.maxAccumulateCarryforward = maxAccumulateCarryforward;
}


public void setLvTypeId(int lvTypeId){
    this.lvTypeId = lvTypeId;
}


public int getMinNoDays(){
    return minNoDays;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public int getLvsAllotedLeaves(){
    return lvsAllotedLeaves;
}


public void setIsCarryforward(int isCarryforward){
    this.isCarryforward = isCarryforward;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setLvsAllotedLeaves(int lvsAllotedLeaves){
    this.lvsAllotedLeaves = lvsAllotedLeaves;
}


public int getMaxAccumulateCarryforward(){
    return maxAccumulateCarryforward;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public int getIsCarryforward(){
    return isCarryforward;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public int getMaxNoDays(){
    return maxNoDays;
}


public int getMakerUserId(){
    return makerUserId;
}


public int getLvsId(){
    return lvsId;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setMinNoDays(int minNoDays){
    this.minNoDays = minNoDays;
}


public int getLvsDetailsId(){
    return lvsDetailsId;
}


public String getMakerDatetime(){
    return makerDatetime;
}


public int getIsActive(){
    return isActive;
}


public int getMaxCarryforward(){
    return maxCarryforward;
}


public void setMaxCarryforward(int maxCarryforward){
    this.maxCarryforward = maxCarryforward;
}


public int getDelStatus(){
    return delStatus;
}


public void setLvsId(int lvsId){
    this.lvsId = lvsId;
}


public int getLvTypeId(){
    return lvTypeId;
}


@Override
public String toString(){
    return "LeaveStructureDetails [lvsDetailsId=" + lvsDetailsId + ", lvsId=" + lvsId + ", lvTypeId=" + lvTypeId + ", lvsAllotedLeaves=" + lvsAllotedLeaves + ", minNoDays=" + minNoDays + ", maxNoDays=" + maxNoDays + ", isCarryforward=" + isCarryforward + ", maxCarryforward=" + maxCarryforward + ", maxAccumulateCarryforward=" + maxAccumulateCarryforward + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setLvsDetailsId(int lvsDetailsId){
    this.lvsDetailsId = lvsDetailsId;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIsActive(int isActive){
    this.isActive = isActive;
}


}