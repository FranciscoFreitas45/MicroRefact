import javax.persistence;
public class ClaimStructureDetail {

 private  int clmStructDetailsId;

 private  int clmStructHeadId;

 private  int clmTypeId;

 private  float clmAmt;

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


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public float getClmAmt(){
    return clmAmt;
}


public int getClmStructDetailsId(){
    return clmStructDetailsId;
}


public int getClmStructHeadId(){
    return clmStructHeadId;
}


public int getClmTypeId(){
    return clmTypeId;
}


public int getMakerUserId(){
    return makerUserId;
}


public String getMakerDatetime(){
    return makerDatetime;
}


public int getIsActive(){
    return isActive;
}


public int getDelStatus(){
    return delStatus;
}


}