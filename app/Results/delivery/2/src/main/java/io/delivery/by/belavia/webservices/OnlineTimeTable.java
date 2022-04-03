package io.delivery.by.belavia.webservices;
 import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;
@WebServiceClient(name = "OnlineTimeTable", targetNamespace = "http://webservices.belavia.by/", wsdlLocation = "http://86.57.245.235/TimeTable/Service.asmx?WSDL")
public class OnlineTimeTable extends Service{

 private  URL ONLINETIMETABLE_WSDL_LOCATION;

 private  WebServiceException ONLINETIMETABLE_EXCEPTION;

 private  QName ONLINETIMETABLE_QNAME;


@WebEndpoint(name = "OnlineTimeTableSoap")
public OnlineTimeTableSoap getOnlineTimeTableSoap(WebServiceFeature features){
    return super.getPort(new QName("http://webservices.belavia.by/", "OnlineTimeTableSoap"), OnlineTimeTableSoap.class, features);
}


public URL __getWsdlLocation(){
    if (ONLINETIMETABLE_EXCEPTION != null) {
        throw ONLINETIMETABLE_EXCEPTION;
    }
    return ONLINETIMETABLE_WSDL_LOCATION;
}


}