import javax.persistence;
public class EmpSalAllowance {

 private  int empSalAllowanceId;

 private  int empId;

 private  int allowanceId;

 private  Double allowanceValue;

 private  String makerEnterDatetime;

 private  int delStatus;

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


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public int getEmpId(){
    return empId;
}


public Double getAllowanceValue(){
    return allowanceValue;
}


public int getEmpSalAllowanceId(){
    return empSalAllowanceId;
}


public int getDelStatus(){
    return delStatus;
}


public int getAllowanceId(){
    return allowanceId;
}


}