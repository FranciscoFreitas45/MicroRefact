import javax.persistence;
public class PayDeductionDetails {

 private  int dedId;

 private  int empId;

 private  int cmpId;

 private  int dedTypeId;

 private  double dedRate;

 private  int dedOccurence;

 private  int dedTotal;

 private  String dedRemark;

 private  String dedLoginName;

 private  String dedLoginDteTime;

 private  String dedApproveBy;

 private  String dedApprovalRemark;

 private  String dedApprovalDatetime;

 private  int isDeducted;

 private  int finalStatus;

 private  int month;

 private  int year;

 private  int delStatus;

 private  String makerEnterDatetime;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public int getDedTypeId(){
    return dedTypeId;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


public int getDedId(){
    return dedId;
}


public String getExVar1(){
    return exVar1;
}


public int getCmpId(){
    return cmpId;
}


public String getDedApproveBy(){
    return dedApproveBy;
}


public String getDedApprovalRemark(){
    return dedApprovalRemark;
}


public String getDedLoginDteTime(){
    return dedLoginDteTime;
}


public int getMonth(){
    return month;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getIsDeducted(){
    return isDeducted;
}


public int getDedTotal(){
    return dedTotal;
}


public int getEmpId(){
    return empId;
}


public double getDedRate(){
    return dedRate;
}


public int getDedOccurence(){
    return dedOccurence;
}


public String getDedLoginName(){
    return dedLoginName;
}


public int getFinalStatus(){
    return finalStatus;
}


public String getDedRemark(){
    return dedRemark;
}


public int getYear(){
    return year;
}


public int getDelStatus(){
    return delStatus;
}


public String getDedApprovalDatetime(){
    return dedApprovalDatetime;
}


}