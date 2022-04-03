package net.webservicex;
 import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;
@WebServiceClient(name = "country", targetNamespace = "http://www.webserviceX.NET", wsdlLocation = "http://www.webservicex.net/country.asmx?WSDL")
public class Country extends Service{

 private  URL COUNTRY_WSDL_LOCATION;

 private  WebServiceException COUNTRY_EXCEPTION;

 private  QName COUNTRY_QNAME;

public Country() {
    super(__getWsdlLocation(), COUNTRY_QNAME);
}public Country(WebServiceFeature... features) {
    super(__getWsdlLocation(), COUNTRY_QNAME, features);
}public Country(URL wsdlLocation) {
    super(wsdlLocation, COUNTRY_QNAME);
}public Country(URL wsdlLocation, WebServiceFeature... features) {
    super(wsdlLocation, COUNTRY_QNAME, features);
}public Country(URL wsdlLocation, QName serviceName) {
    super(wsdlLocation, serviceName);
}public Country(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
    super(wsdlLocation, serviceName, features);
}
@WebEndpoint(name = "countrySoap")
public CountrySoap getCountrySoap(WebServiceFeature features){
    return super.getPort(new QName("http://www.webserviceX.NET", "countrySoap"), CountrySoap.class, features);
}


public URL __getWsdlLocation(){
    if (COUNTRY_EXCEPTION != null) {
        throw COUNTRY_EXCEPTION;
    }
    return COUNTRY_WSDL_LOCATION;
}


}