package com.poseidon.customer.web.form;
 import com.poseidon.customer.domain.CustomerVO;
import java.util.List;
import java.util.StringJoiner;
public class CustomerForm {

 private  Long id;

 private  List<CustomerVO> customerVOs;

 private  CustomerVO currentCustomerVO;

 private  CustomerVO searchCustomerVO;

 private  String loggedInRole;

 private  String loggedInUser;

 private  String statusMessage;

 private  String statusMessageType;


public void setStatusMessage(String statusMessage){
    this.statusMessage = statusMessage;
}


public String getStatusMessage(){
    return statusMessage;
}


public void setCustomerVOs(List<CustomerVO> customerVOs){
    this.customerVOs = customerVOs;
}


public void setLoggedInRole(String loggedInRole){
    this.loggedInRole = loggedInRole;
}


public Long getId(){
    return id;
}


public void setLoggedInUser(String loggedInUser){
    this.loggedInUser = loggedInUser;
}


public void setStatusMessageType(String statusMessageType){
    this.statusMessageType = statusMessageType;
}


public List<CustomerVO> getCustomerVOs(){
    return customerVOs;
}


public CustomerVO getSearchCustomerVO(){
    return searchCustomerVO;
}


public String getStatusMessageType(){
    return statusMessageType;
}


public void setCurrentCustomerVO(CustomerVO currentCustomerVO){
    this.currentCustomerVO = currentCustomerVO;
}


public void setId(Long id){
    this.id = id;
}


public CustomerVO getCurrentCustomerVO(){
    return currentCustomerVO;
}


public String getLoggedInUser(){
    return loggedInUser;
}


@Override
public String toString(){
    return new StringJoiner(", ", CustomerForm.class.getSimpleName() + "[", "]").add("id=" + id).add("customerVOs=" + customerVOs).add("currentCustomerVO=" + currentCustomerVO).add("searchCustomerVO=" + searchCustomerVO).add("loggedInRole='" + loggedInRole + "'").add("loggedInUser='" + loggedInUser + "'").add("statusMessage='" + statusMessage + "'").add("statusMessageType='" + statusMessageType + "'").toString();
}


public void setSearchCustomerVO(CustomerVO searchCustomerVO){
    this.searchCustomerVO = searchCustomerVO;
}


public String getLoggedInRole(){
    return loggedInRole;
}


}