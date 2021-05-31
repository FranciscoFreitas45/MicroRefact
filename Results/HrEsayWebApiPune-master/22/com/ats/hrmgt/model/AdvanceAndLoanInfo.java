public class AdvanceAndLoanInfo {

 private  double advanceAmt;

 private  double loanAmt;


public double getLoanAmt(){
    return loanAmt;
}


public double getAdvanceAmt(){
    return advanceAmt;
}


public void setLoanAmt(double loanAmt){
    this.loanAmt = loanAmt;
}


public void setAdvanceAmt(double advanceAmt){
    this.advanceAmt = advanceAmt;
}


@Override
public String toString(){
    return "AdvanceAndLoanInfo [advanceAmt=" + advanceAmt + ", loanAmt=" + loanAmt + "]";
}


}