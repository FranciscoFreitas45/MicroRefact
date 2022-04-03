package com.netease.dto;
 import java.util.ArrayList;
import java.util.List;
public class LoanRecordDTO {

 private  List<LoanRecordAbstractDTO> loanRecordAbstractDTOList;

 private  List<LoanDetailDTO> loanDetailDTOList;


public void setLoanDetailDTOList(List<LoanDetailDTO> loanDetailDTOList){
    this.loanDetailDTOList = loanDetailDTOList;
}


public void setLoanRecordAbstractDTOList(List<LoanRecordAbstractDTO> loanRecordAbstractDTOList){
    this.loanRecordAbstractDTOList = loanRecordAbstractDTOList;
}


public List<LoanDetailDTO> getLoanDetailDTOList(){
    return loanDetailDTOList;
}


public List<LoanRecordAbstractDTO> getLoanRecordAbstractDTOList(){
    return loanRecordAbstractDTOList;
}


}