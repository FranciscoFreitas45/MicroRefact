import javax.persistence;
public class Location {

 private  int locId;

 private  int compId;

 private  String locName;

 private  String locNameShort;

 private  String locShortAddress;

 private  String locHrContactPerson;

 private  String locHrContactNumber;

 private  String locHrContactEmail;

 private  String locRemarks;

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


public String getLocNameShort(){
    return locNameShort;
}


public String getLocName(){
    return locName;
}


public int getExInt1(){
    return exInt1;
}


public int getLocId(){
    return locId;
}


public String getLocHrContactEmail(){
    return locHrContactEmail;
}


public String getExVar1(){
    return exVar1;
}


public String getLocRemarks(){
    return locRemarks;
}


public int getCompId(){
    return compId;
}


public String getLocHrContactNumber(){
    return locHrContactNumber;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getMakerUserId(){
    return makerUserId;
}


public String getLocHrContactPerson(){
    return locHrContactPerson;
}


public int getIsActive(){
    return isActive;
}


public String getLocShortAddress(){
    return locShortAddress;
}


public int getDelStatus(){
    return delStatus;
}


}