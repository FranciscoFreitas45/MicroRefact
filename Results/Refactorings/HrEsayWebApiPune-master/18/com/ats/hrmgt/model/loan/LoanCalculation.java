public class LoanCalculation {

 private  float repayAmt;

 private  float emiAmt;

 private  String calDate;


public void setCalDate(String calDate){
    this.calDate = calDate;
}


public String getCalDate(){
    return calDate;
}


public float getRepayAmt(){
    return repayAmt;
}


@Override
public String toString(){
    return "LoanCalculation [repayAmt=" + repayAmt + ", emiAmt=" + emiAmt + ", calDate=" + calDate + "]";
}


public float getEmiAmt(){
    return emiAmt;
}


public void setRepayAmt(float repayAmt){
    this.repayAmt = repayAmt;
}


public void setEmiAmt(float empAmt){
    this.emiAmt = empAmt;
}


}