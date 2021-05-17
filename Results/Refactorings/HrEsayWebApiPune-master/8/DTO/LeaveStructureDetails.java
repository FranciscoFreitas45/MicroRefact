import javax.persistence;
public class LeaveStructureDetails {

 private  int lvsDetailsId;

 private  int lvsId;

 private  int lvTypeId;

 private  int lvsAllotedLeaves;

 private  int minNoDays;

 private  int maxNoDays;

 private  int isCarryforward;

 private  int maxCarryforward;

 private  int maxAccumulateCarryforward;

 private  int delStatus;

 private  int isActive;

 private  int makerUserId;

 private  String makerDatetime;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public int getExInt2(){
    return exInt2;
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


public int getMaxAccumulateCarryforward(){
    return maxAccumulateCarryforward;
}


public int getIsCarryforward(){
    return isCarryforward;
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


public int getDelStatus(){
    return delStatus;
}


public int getLvTypeId(){
    return lvTypeId;
}


}