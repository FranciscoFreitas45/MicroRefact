import javax.persistence;
public class SalAllownceCal {

 private  int empSalAllowanceId;

 private  int salaryCalcId;

 private  int empId;

 private  int allowanceId;

 private  String shortName;

 private  double allowanceValue;

 private  double allowanceValueCal;

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


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public double getAllowanceValue(){
    return allowanceValue;
}


public double getAllowanceValueCal(){
    return allowanceValueCal;
}


public int getAllowanceId(){
    return allowanceId;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getEmpId(){
    return empId;
}


public int getEmpSalAllowanceId(){
    return empSalAllowanceId;
}


public int getDelStatus(){
    return delStatus;
}


public int getSalaryCalcId(){
    return salaryCalcId;
}


public String getShortName(){
    return shortName;
}


}