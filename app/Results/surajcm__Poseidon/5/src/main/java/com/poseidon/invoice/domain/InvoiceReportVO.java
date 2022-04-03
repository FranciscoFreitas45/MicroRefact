package com.poseidon.invoice.domain;
 public class InvoiceReportVO {

 private  Long invoiceId;

 private  String tagNo;

 private  Long customerId;

 private  String customerName;

 private  String customerAddress;

 private  String description;

 private  String serialNo;

 private  int quantity;

 private  double rate;

 private  double amount;

 private  double totalAmount;

 private  String companyName;

 private  String companyAddress;

 private  String companyPhoneNumber;

 private  String companyWebsite;

 private  String companyEmail;

 private  String companyTerms;

 private  String companyVatTin;

 private  String companyCstTin;


public int getQuantity(){
    return quantity;
}


public void setTagNo(String tagNo){
    this.tagNo = tagNo;
}


public void setDescription(String description){
    this.description = description;
}


public Long getInvoiceId(){
    return invoiceId;
}


public String getDescription(){
    return description;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public String getCompanyTerms(){
    return companyTerms;
}


public Long getCustomerId(){
    return customerId;
}


public void setCompanyWebsite(String companyWebsite){
    this.companyWebsite = companyWebsite;
}


public double getRate(){
    return rate;
}


public void setCompanyPhoneNumber(String companyPhoneNumber){
    this.companyPhoneNumber = companyPhoneNumber;
}


public void setCompanyTerms(String companyTerms){
    this.companyTerms = companyTerms;
}


public void setCompanyCstTin(String companyCstTin){
    this.companyCstTin = companyCstTin;
}


public void setSerialNo(String serialNo){
    this.serialNo = serialNo;
}


public void setAmount(double amount){
    this.amount = amount;
}


public String getSerialNo(){
    return serialNo;
}


public double getAmount(){
    return amount;
}


public double getTotalAmount(){
    return totalAmount;
}


public String getCustomerName(){
    return customerName;
}


public void setCompanyEmail(String companyEmail){
    this.companyEmail = companyEmail;
}


public String getTagNo(){
    return tagNo;
}


public String getCompanyEmail(){
    return companyEmail;
}


public String getCompanyCstTin(){
    return companyCstTin;
}


public void setCustomerName(String customerName){
    this.customerName = customerName;
}


public void setRate(double rate){
    this.rate = rate;
}


public String getCompanyWebsite(){
    return companyWebsite;
}


public void setTotalAmount(double totalAmount){
    this.totalAmount = totalAmount;
}


public String getCompanyPhoneNumber(){
    return companyPhoneNumber;
}


public void setCompanyVatTin(String companyVatTin){
    this.companyVatTin = companyVatTin;
}


public void setInvoiceId(Long invoiceId){
    this.invoiceId = invoiceId;
}


public String getCompanyAddress(){
    return companyAddress;
}


public String getCompanyVatTin(){
    return companyVatTin;
}


public void setQuantity(int quantity){
    this.quantity = quantity;
}


public String getCompanyName(){
    return companyName;
}


public void setCompanyAddress(String companyAddress){
    this.companyAddress = companyAddress;
}


public String getCustomerAddress(){
    return customerAddress;
}


public void setCustomerAddress(String customerAddress){
    this.customerAddress = customerAddress;
}


public void setCustomerId(Long customerId){
    this.customerId = customerId;
}


}