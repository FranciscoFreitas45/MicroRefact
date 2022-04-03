package com.netease.dto;
 public class AllInfoDTO {

 private  BasicInfoDTO basicInfoDTO;

 private  LoanRecordDTO LoanRecordDTO;

 private  PublicRecordDTO publicRecordDTO;

 private  CheckRecordDTO checkRecordDTO;


public void setBasicInfoDTO(BasicInfoDTO basicInfoDTO){
    this.basicInfoDTO = basicInfoDTO;
}


public BasicInfoDTO getBasicInfoDTO(){
    return basicInfoDTO;
}


public LoanRecordDTO getLoanRecordDTO(){
    return LoanRecordDTO;
}


public CheckRecordDTO getCheckRecordDTO(){
    return checkRecordDTO;
}


public void setCheckRecordDTO(CheckRecordDTO checkRecordDTO){
    this.checkRecordDTO = checkRecordDTO;
}


public PublicRecordDTO getPublicRecordDTO(){
    return publicRecordDTO;
}


public void setLoanRecordDTO(LoanRecordDTO loanRecordDTO){
    LoanRecordDTO = loanRecordDTO;
}


public void setPublicRecordDTO(PublicRecordDTO publicRecordDTO){
    this.publicRecordDTO = publicRecordDTO;
}


}