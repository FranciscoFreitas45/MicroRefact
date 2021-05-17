import javax.persistence;
public class WeeklyOff {

 private  int woId;

 private  int companyId;

 private  String woType;

 private  String locId;

 private  String woPresently;

 private  String woDay;

 private  String woRemarks;

 private  int woIsUsed;

 private  int delStatus;

 private  int isActive;

 private  int makerUserId;

 private  String makerEnterDatetime;

 private  int exInt1;

 private  int exInt2;

 private  int exInt3;

 private  String exVar1;

 private  String exVar2;

 private  String exVar3;

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


public String getWoType(){
    return woType;
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


public int getWoId(){
    return woId;
}


public int getWoIsUsed(){
    return woIsUsed;
}


public String getWoRemarks(){
    return woRemarks;
}


public String getWoPresently(){
    return woPresently;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getMakerUserId(){
    return makerUserId;
}


public String getWoDay(){
    return woDay;
}


public int getIsActive(){
    return isActive;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


}