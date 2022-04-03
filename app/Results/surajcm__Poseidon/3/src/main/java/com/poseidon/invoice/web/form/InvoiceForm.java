package com.poseidon.invoice.web.form;
 import com.poseidon.invoice.domain.InvoiceVO;
import java.util.List;
public class InvoiceForm {

 private  Long id;

 private  String loggedInUser;

 private  String loggedInRole;

 private  String statusMessage;

 private  String statusMessageType;

 private  String tagNo;

 private  Long customerId;

 private  String customerName;

 private  String description;

 private  String serialNo;

 private  int quantity;

 private  double rate;

 private  double amount;

 private  double totalAmount;

 private  InvoiceVO searchInvoiceVo;

 private  InvoiceVO currentInvoiceVo;

 private  List<InvoiceVO> invoiceVos;


public int getQuantity(){
    return quantity;
}


public String getStatusMessage(){
    return statusMessage;
}


public void setTagNo(String tagNo){
    this.tagNo = tagNo;
}


public void setCurrentInvoiceVo(InvoiceVO currentInvoiceVo){
    this.currentInvoiceVo = currentInvoiceVo;
}


public Long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setLoggedInUser(String loggedInUser){
    this.loggedInUser = loggedInUser;
}


public Long getCustomerId(){
    return customerId;
}


public InvoiceVO getCurrentInvoiceVo(){
    return currentInvoiceVo;
}


public double getRate(){
    return rate;
}


public String getStatusMessageType(){
    return statusMessageType;
}


public List<InvoiceVO> getInvoiceVos(){
    return invoiceVos;
}


public String getLoggedInUser(){
    return loggedInUser;
}


public void setId(Long id){
    this.id = id;
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


public void setStatusMessage(String statusMessage){
    this.statusMessage = statusMessage;
}


public double getTotalAmount(){
    return totalAmount;
}


public String getCustomerName(){
    return customerName;
}


public void setLoggedInRole(String loggedInRole){
    this.loggedInRole = loggedInRole;
}


public String getTagNo(){
    return tagNo;
}


public void setCustomerName(String customerName){
    this.customerName = customerName;
}


public void setSearchInvoiceVo(InvoiceVO searchInvoiceVo){
    this.searchInvoiceVo = searchInvoiceVo;
}


public void setRate(double rate){
    this.rate = rate;
}


public void setStatusMessageType(String statusMessageType){
    this.statusMessageType = statusMessageType;
}


public void setTotalAmount(double totalAmount){
    this.totalAmount = totalAmount;
}


public void setQuantity(int quantity){
    this.quantity = quantity;
}


public void setInvoiceVos(List<InvoiceVO> invoiceVos){
    this.invoiceVos = invoiceVos;
}


public InvoiceVO getSearchInvoiceVo(){
    return searchInvoiceVo;
}


@Override
public String toString(){
    return "InvoiceForm{" + "id=" + id + ", loggedInUser='" + loggedInUser + '\'' + ", loggedInRole='" + loggedInRole + '\'' + ", statusMessage='" + statusMessage + '\'' + ", statusMessageType='" + statusMessageType + '\'' + ", tagNo='" + tagNo + '\'' + ", customerId=" + customerId + ", customerName='" + customerName + '\'' + ", description='" + description + '\'' + ", serialNo='" + serialNo + '\'' + ", quantity=" + quantity + ", rate=" + rate + ", amount=" + amount + ", totalAmount=" + totalAmount + ", searchInvoiceVo=" + searchInvoiceVo + ", currentInvoiceVo=" + currentInvoiceVo + ", invoiceVos=" + invoiceVos + '}';
}


public String getLoggedInRole(){
    return loggedInRole;
}


public void setCustomerId(Long customerId){
    this.customerId = customerId;
}


}