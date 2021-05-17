import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
public class LoanMain {

 private  int id;

 private  int cmpId;

 private  int empId;

 private  String loanApplNo;

 private  int loanAmt;

 private  double loanRoi;

 private  int loanTenure;

 private  Date loanRepayStart;

 private  Date loanRepayEnd;

 private  int loanRepayAmt;

 private  int loanEmi;

 private  int loanEmiIntrest;

 private  int currentTotpaid;

 private  int currentOutstanding;

 private  String loanStatus;

 private  String loginName;

 private  String loginTime;

 private  String allData;

 private  String remark;

 private  int skipId;

 private  Date loanAddDate;

 private  String skipLoginName;

 private  String skipLoginTime;

 private  String skipRemarks;

 private  int delStatus;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public int getLoanEmiIntrest(){
    return loanEmiIntrest;
}


public String getSkipLoginTime(){
    return skipLoginTime;
}


public String getExVar1(){
    return exVar1;
}


public int getCmpId(){
    return cmpId;
}


public int getCurrentOutstanding(){
    return currentOutstanding;
}


public int getLoanRepayAmt(){
    return loanRepayAmt;
}


public String getRemark(){
    return remark;
}


public int getLoanEmi(){
    return loanEmi;
}


public int getSkipId(){
    return skipId;
}


public String getSkipRemarks(){
    return skipRemarks;
}


public String getLoanStatus(){
    return loanStatus;
}


public int getEmpId(){
    return empId;
}


public String getLoginName(){
    return loginName;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getLoanAddDate(){
    return loanAddDate;
}


public String getSkipLoginName(){
    return skipLoginName;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getLoanRepayStart(){
    return loanRepayStart;
}


public int getId(){
    return id;
}


public int getCurrentTotpaid(){
    return currentTotpaid;
}


public String getAllData(){
    return allData;
}


public String getLoginTime(){
    return loginTime;
}


public int getLoanAmt(){
    return loanAmt;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getLoanRepayEnd(){
    return loanRepayEnd;
}


public String getLoanApplNo(){
    return loanApplNo;
}


public double getLoanRoi(){
    return loanRoi;
}


public int getLoanTenure(){
    return loanTenure;
}


public int getDelStatus(){
    return delStatus;
}


}