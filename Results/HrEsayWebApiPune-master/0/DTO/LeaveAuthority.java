import javax.persistence;
public class LeaveAuthority {

 private  int laPkey;

 private  int empId;

 private  int companyId;

 private  int iniAuthEmpId;

 private  int finAuthEmpId;

 private  String repToEmpIds;

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


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public int getFinAuthEmpId(){
    return finAuthEmpId;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getLaPkey(){
    return laPkey;
}


public int getMakerUserId(){
    return makerUserId;
}


public int getIniAuthEmpId(){
    return iniAuthEmpId;
}


public int getEmpId(){
    return empId;
}


public int getIsActive(){
    return isActive;
}


public String getRepToEmpIds(){
    return repToEmpIds;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


}