package io.delivery.by.belavia.webservices;
 import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
@WebService(name = "OnlineTimeTableSoap", targetNamespace = "http://webservices.belavia.by/")
@XmlSeeAlso({ ObjectFactory.class })
public interface OnlineTimeTableSoap {


@WebMethod(operationName = "GetTimeTable", action = "http://webservices.belavia.by/GetTimeTable")
@WebResult(name = "GetTimeTableResult", targetNamespace = "http://webservices.belavia.by/")
@RequestWrapper(localName = "GetTimeTable", targetNamespace = "http://webservices.belavia.by/", className = "GetTimeTable")
@ResponseWrapper(localName = "GetTimeTableResponse", targetNamespace = "http://webservices.belavia.by/", className = "GetTimeTableResponse")
public TimeTableResponce getTimeTable(String airport,TimeTableType type,XMLGregorianCalendar viewDate)
;

@WebMethod(operationName = "GetAirportsList", action = "http://webservices.belavia.by/GetAirportsList")
@WebResult(name = "GetAirportsListResult", targetNamespace = "http://webservices.belavia.by/")
@RequestWrapper(localName = "GetAirportsList", targetNamespace = "http://webservices.belavia.by/", className = "GetAirportsList")
@ResponseWrapper(localName = "GetAirportsListResponse", targetNamespace = "http://webservices.belavia.by/", className = "GetAirportsListResponse")
public AirportsResponse getAirportsList(String language)
;

}