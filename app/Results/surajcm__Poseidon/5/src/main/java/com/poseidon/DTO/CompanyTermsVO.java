package com.poseidon.DTO;
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


public void setCompanyEmail(String companyEmail){
    this.companyEmail = companyEmail;
}


public String getCompanyEmail(){
    return companyEmail;
}


public String getCompanyCstTin(){
    return companyCstTin;
}


public String getCompanyTerms(){
    return companyTerms;
}


public String getCompanyWebsite(){
    return companyWebsite;
}


public String getCompanyPhoneNumber(){
    return companyPhoneNumber;
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


public void setCompanyTerms(String companyTerms){
    this.companyTerms = companyTerms;
}


@Override
public String toString(){
    return "CompanyTermsVO{" + "companyName='" + companyName + '\'' + ", companyAddress='" + companyAddress + '\'' + ", companyPhoneNumber='" + companyPhoneNumber + '\'' + ", companyWebsite='" + companyWebsite + '\'' + ", companyEmail='" + companyEmail + '\'' + ", companyTerms='" + companyTerms + '\'' + ", termsAndConditions='" + termsAndConditions + '\'' + ", companyDetails='" + companyDetails + '\'' + ", companyVatTin='" + companyVatTin + '\'' + ", companyCstTin='" + companyCstTin + '\'' + '}';
}


public String getTermsAndConditions(){
    return termsAndConditions;
}


}