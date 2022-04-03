package com.credit.dto;
 import java.util.ArrayList;
import java.util.List;
public class LoanRecordDTO {

 private  List<LoanRecordAbstractDTO> loanRecordAbstractDTOList;

 private  List<String> creditDetail;

 private  List<String> otherLoanOverdueDetail;

 private  List<String> otherLoanUnoverdueDetail;


public void setOtherLoanOverdueDetail(List<String> otherLoanOverdueDetail){
    this.otherLoanOverdueDetail = otherLoanOverdueDetail;
}


public void setLoanRecordAbstractDTOList(List<LoanRecordAbstractDTO> loanRecordAbstractDTOList){
    this.loanRecordAbstractDTOList = loanRecordAbstractDTOList;
}


public void setCreditDetail(List<String> creditDetail){
    this.creditDetail = creditDetail;
}


public void setOtherLoanUnoverdueDetail(List<String> otherLoanUnoverdueDetail){
    this.otherLoanUnoverdueDetail = otherLoanUnoverdueDetail;
}


public List<String> getOtherLoanOverdueDetail(){
    return otherLoanOverdueDetail;
}


public List<LoanRecordAbstractDTO> getLoanRecordAbstractDTOList(){
    return loanRecordAbstractDTOList;
}


public List<String> getOtherLoanUnoverdueDetail(){
    return otherLoanUnoverdueDetail;
}


public List<String> getCreditDetail(){
    return creditDetail;
}


}