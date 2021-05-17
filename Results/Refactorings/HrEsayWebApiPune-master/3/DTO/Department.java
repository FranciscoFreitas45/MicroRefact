import javax.persistence;
public class Department {

 private  int departId;

 private  String name;

 private  String nameSd;

 private  String remarks;

 private  int isActive;

 private  int companyId;

 private  int delStatus;

 private  String makerEnterDatetime;

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


public String getName(){
    return name;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public String getNameSd(){
    return nameSd;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public String getRemarks(){
    return remarks;
}


public int getIsActive(){
    return isActive;
}


public int getDelStatus(){
    return delStatus;
}


public int getDepartId(){
    return departId;
}


public int getCompanyId(){
    return companyId;
}


}