package org.russianpost.operationhistory;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.russianpost.operationhistory.data.AuthorizationHeader;
import org.russianpost.operationhistory.data.OperationHistoryRequest;
import Interface.OperationHistoryRequest;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOperationHistory", propOrder = { "operationHistoryRequest", "authorizationHeader" })
public class GetOperationHistory {

@XmlElement(name = "OperationHistoryRequest", namespace = "http://russianpost.org/operationhistory/data")
 protected  OperationHistoryRequest operationHistoryRequest;

@XmlElement(name = "AuthorizationHeader", namespace = "http://russianpost.org/operationhistory/data")
 protected  AuthorizationHeader authorizationHeader;


public OperationHistoryRequest getOperationHistoryRequest(){
    return operationHistoryRequest;
}


public void setAuthorizationHeader(AuthorizationHeader value){
    this.authorizationHeader = value;
}


public AuthorizationHeader getAuthorizationHeader(){
    return authorizationHeader;
}


public void setOperationHistoryRequest(OperationHistoryRequest value){
    this.operationHistoryRequest = value;
}


}