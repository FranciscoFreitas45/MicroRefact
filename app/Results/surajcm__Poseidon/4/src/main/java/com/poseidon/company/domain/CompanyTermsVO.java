package com.poseidon.company.domain;
 import com.poseidon.init.domain.CommonVO;
public class CompanyTermsVO extends CommonVO{

 private  String companyName;

 private  String companyAddress;

 private  String companyPhoneNumber;

 private  String companyWebsite;

 private  String companyEmail;

 private  String companyTerms;

 private  String termsAndConditions;

 private  String companyDetails;

 private  String companyVatTin;

 private  String companyCstTin;


public String getCompanyDetails(){
    return companyDetails;
}


public void setTermsAndConditions(String termsAndConditions){
    this.termsAndConditions = termsAndConditions;
}


public void setCompanyEmail(String companyEmail){
    this.companyEmail = companyEmail;
}


public String getCompanyEmail(){
    return companyEmail;
}


public void setCompanyDetails(String companyDetails){
    this.companyDetails = companyDetails;
}


public String getCompanyCstTin(){
    return companyCstTin;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public String getCompanyTerms(){
    return companyTerms;
}


public String getCompanyWebsite(){
    return companyWebsite;
}


public void setCompanyWebsite(String companyWebsite){
    this.companyWebsite = companyWebsite;
}


public String getCompanyPhoneNumber(){
    return companyPhoneNumber;
}


public void setCompanyPhoneNumber(String companyPhoneNumber){
    this.companyPhoneNumber = companyPhoneNumber;
}


public void setCompanyVatTin(String companyVatTin){
    this.companyVatTin = companyVatTin;
}


public String getCompanyAddress(){
    return companyAddress;
}


public String getCompanyVatTin(){
    return companyVatTin;
}


public String getCompanyName(){
    return companyName;
}


public void setCompanyAddress(String companyAddress){
    this.companyAddress = companyAddress;
}


public void setCompanyTerms(String companyTerms){
    this.companyTerms = companyTerms;
}


public void setCompanyCstTin(String companyCstTin){
    this.companyCstTin = companyCstTin;
}


@Override
public String toString(){
    return "CompanyTermsVO{" + "companyName='" + companyName + '\'' + ", companyAddress='" + companyAddress + '\'' + ", companyPhoneNumber='" + companyPhoneNumber + '\'' + ", companyWebsite='" + companyWebsite + '\'' + ", companyEmail='" + companyEmail + '\'' + ", companyTerms='" + companyTerms + '\'' + ", termsAndConditions='" + termsAndConditions + '\'' + ", companyDetails='" + companyDetails + '\'' + ", companyVatTin='" + companyVatTin + '\'' + ", companyCstTin='" + companyCstTin + '\'' + '}';
}


public String getTermsAndConditions(){
    return termsAndConditions;
}


}