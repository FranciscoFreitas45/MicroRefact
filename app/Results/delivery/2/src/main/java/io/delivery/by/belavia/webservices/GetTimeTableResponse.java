package io.delivery.by.belavia.webservices;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getTimeTableResult" })
@XmlRootElement(name = "GetTimeTableResponse")
public class GetTimeTableResponse {

@XmlElement(name = "GetTimeTableResult")
 protected  TimeTableResponce getTimeTableResult;


public TimeTableResponce getGetTimeTableResult(){
    return getTimeTableResult;
}


public void setGetTimeTableResult(TimeTableResponce value){
    this.getTimeTableResult = value;
}


}