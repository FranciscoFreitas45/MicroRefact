package org.russianpost.operationhistory.data;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationHistoryRecord", propOrder = { "addressParameters", "financeParameters", "itemParameters", "operationParameters", "userParameters" })
public class OperationHistoryRecord {

@XmlElement(name = "AddressParameters", required = true)
 protected  AddressParameters addressParameters;

@XmlElement(name = "FinanceParameters", required = true)
 protected  FinanceParameters financeParameters;

@XmlElement(name = "ItemParameters", required = true)
 protected  ItemParameters itemParameters;

@XmlElement(name = "OperationParameters", required = true)
 protected  OperationParameters operationParameters;

@XmlElement(name = "UserParameters", required = true)
 protected  UserParameters userParameters;


public AddressParameters getAddressParameters(){
    return addressParameters;
}


public void setFinanceParameters(FinanceParameters value){
    this.financeParameters = value;
}


public UserParameters getUserParameters(){
    return userParameters;
}


public FinanceParameters getFinanceParameters(){
    return financeParameters;
}


public void setOperationParameters(OperationParameters value){
    this.operationParameters = value;
}


public void setUserParameters(UserParameters value){
    this.userParameters = value;
}


public OperationParameters getOperationParameters(){
    return operationParameters;
}


public void setItemParameters(ItemParameters value){
    this.itemParameters = value;
}


public void setAddressParameters(AddressParameters value){
    this.addressParameters = value;
}


public ItemParameters getItemParameters(){
    return itemParameters;
}


}