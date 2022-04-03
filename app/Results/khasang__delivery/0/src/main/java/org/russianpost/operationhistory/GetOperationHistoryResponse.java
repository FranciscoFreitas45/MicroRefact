package org.russianpost.operationhistory;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.russianpost.operationhistory.data.OperationHistoryData;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOperationHistoryResponse", propOrder = { "operationHistoryData" })
public class GetOperationHistoryResponse {

@XmlElement(name = "OperationHistoryData", namespace = "http://russianpost.org/operationhistory/data")
 protected  OperationHistoryData operationHistoryData;


public OperationHistoryData getOperationHistoryData(){
    return operationHistoryData;
}


public void setOperationHistoryData(OperationHistoryData value){
    this.operationHistoryData = value;
}


}