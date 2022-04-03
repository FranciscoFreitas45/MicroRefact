package org.russianpost.operationhistory.data;
 import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateOperationRequest", propOrder = { "requestType", "sourceOperation", "targetOperation", "reasonDescription", "initiatorDepartment", "executorIP" })
public class UpdateOperationRequest {

@XmlElement(name = "RequestType", required = true)
@XmlSchemaType(name = "string")
 protected  RequestType requestType;

@XmlElement(name = "SourceOperation", required = true)
 protected  OperationHistoryRecord sourceOperation;

@XmlElement(name = "TargetOperation")
 protected  OperationHistoryRecord targetOperation;

@XmlElement(name = "ReasonDescription", required = true)
 protected  String reasonDescription;

@XmlElement(name = "InitiatorDepartment", required = true)
 protected  BigInteger initiatorDepartment;

@XmlElement(name = "ExecutorIP", required = true)
 protected  String executorIP;


public RequestType getRequestType(){
    return requestType;
}


public BigInteger getInitiatorDepartment(){
    return initiatorDepartment;
}


public void setInitiatorDepartment(BigInteger value){
    this.initiatorDepartment = value;
}


public void setSourceOperation(OperationHistoryRecord value){
    this.sourceOperation = value;
}


public void setReasonDescription(String value){
    this.reasonDescription = value;
}


public OperationHistoryRecord getSourceOperation(){
    return sourceOperation;
}


public OperationHistoryRecord getTargetOperation(){
    return targetOperation;
}


public void setTargetOperation(OperationHistoryRecord value){
    this.targetOperation = value;
}


public String getExecutorIP(){
    return executorIP;
}


public String getReasonDescription(){
    return reasonDescription;
}


public void setExecutorIP(String value){
    this.executorIP = value;
}


public void setRequestType(RequestType value){
    this.requestType = value;
}


}