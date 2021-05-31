import javax.persistence;
public class Allowances {

 private  int allowanceId;

 private  String name;

 private  String shortName;

 private  int shortNo;

 private  String description;

 private  String isTaxable;

 private  int delStatus;

 private  int isActive;

 private  String makerEnterDatetime;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;

 private  float grossSalPer;

 private  float taxPer;


public String getExVar2(){
    return exVar2;
}


public int getExInt2(){
    return exInt2;
}


public String getName(){
    return name;
}


public int getExInt1(){
    return exInt1;
}


public String getIsTaxable(){
    return isTaxable;
}


public String getExVar1(){
    return exVar1;
}


public String getDescription(){
    return description;
}


public int getAllowanceId(){
    return allowanceId;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getShortNo(){
    return shortNo;
}


public int getIsActive(){
    return isActive;
}


public float getGrossSalPer(){
    return grossSalPer;
}


public int getDelStatus(){
    return delStatus;
}


public String getShortName(){
    return shortName;
}


public float getTaxPer(){
    return taxPer;
}


}