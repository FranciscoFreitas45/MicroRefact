package io.delivery.net.webservicex;
 import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;
@WebServiceClient(name = "GlobalWeather", targetNamespace = "http://www.webserviceX.NET", wsdlLocation = "http://www.webservicex.net/globalweather.asmx?WSDL")
public class GlobalWeather extends Service{

 private  URL GLOBALWEATHER_WSDL_LOCATION;

 private  WebServiceException GLOBALWEATHER_EXCEPTION;

 private  QName GLOBALWEATHER_QNAME;

public GlobalWeather() {
    super(__getWsdlLocation(), GLOBALWEATHER_QNAME);
}public GlobalWeather(WebServiceFeature... features) {
    super(__getWsdlLocation(), GLOBALWEATHER_QNAME, features);
}public GlobalWeather(URL wsdlLocation) {
    super(wsdlLocation, GLOBALWEATHER_QNAME);
}public GlobalWeather(URL wsdlLocation, WebServiceFeature... features) {
    super(wsdlLocation, GLOBALWEATHER_QNAME, features);
}public GlobalWeather(URL wsdlLocation, QName serviceName) {
    super(wsdlLocation, serviceName);
}public GlobalWeather(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
    super(wsdlLocation, serviceName, features);
}
@WebEndpoint(name = "GlobalWeatherSoap")
public GlobalWeatherSoap getGlobalWeatherSoap(WebServiceFeature features){
    return super.getPort(new QName("http://www.webserviceX.NET", "GlobalWeatherSoap"), GlobalWeatherSoap.class, features);
}


public URL __getWsdlLocation(){
    if (GLOBALWEATHER_EXCEPTION != null) {
        throw GLOBALWEATHER_EXCEPTION;
    }
    return GLOBALWEATHER_WSDL_LOCATION;
}


}