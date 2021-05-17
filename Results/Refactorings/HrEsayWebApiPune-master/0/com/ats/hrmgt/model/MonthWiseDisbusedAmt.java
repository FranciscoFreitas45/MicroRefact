public class MonthWiseDisbusedAmt {

 private  String month;

 private  float advAmt;

 private  float loanAmt;


public float getLoanAmt(){
    return loanAmt;
}


public void setMonth(String month){
    this.month = month;
}


public void setLoanAmt(float loanAmt){
    this.loanAmt = loanAmt;
}


@Override
public String toString(){
    return "MonthWiseDisbusedAmt [month=" + month + ", advAmt=" + advAmt + ", loanAmt=" + loanAmt + "]";
}


public String getMonth(){
    return month;
}


public void setAdvAmt(float advAmt){
    this.advAmt = advAmt;
}


public float getAdvAmt(){
    return advAmt;
}


}