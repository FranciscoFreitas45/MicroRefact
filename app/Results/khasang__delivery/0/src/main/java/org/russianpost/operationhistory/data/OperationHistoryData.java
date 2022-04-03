package org.russianpost.operationhistory.data;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "historyRecord" })
@XmlRootElement(name = "OperationHistoryData")
public class OperationHistoryData {

@XmlElement(nillable = true)
 protected  List<OperationHistoryRecord> historyRecord;


public List<OperationHistoryRecord> getHistoryRecord(){
    if (historyRecord == null) {
        historyRecord = new ArrayList<OperationHistoryRecord>();
    }
    return this.historyRecord;
}


}