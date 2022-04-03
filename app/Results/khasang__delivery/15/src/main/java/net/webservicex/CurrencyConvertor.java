package net.webservicex;
 import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;
@WebServiceClient(name = "CurrencyConvertor", targetNamespace = "http://www.webserviceX.NET/", wsdlLocation = "http://www.webservicex.net/CurrencyConvertor.asmx?WSDL")
public class CurrencyConvertor extends Service{

 private  URL CURRENCYCONVERTOR_WSDL_LOCATION;

 private  WebServiceException CURRENCYCONVERTOR_EXCEPTION;

 private  QName CURRENCYCONVERTOR_QNAME;

public CurrencyConvertor() {
    super(__getWsdlLocation(), CURRENCYCONVERTOR_QNAME);
}public CurrencyConvertor(WebServiceFeature... features) {
    super(__getWsdlLocation(), CURRENCYCONVERTOR_QNAME, features);
}public CurrencyConvertor(URL wsdlLocation) {
    super(wsdlLocation, CURRENCYCONVERTOR_QNAME);
}public CurrencyConvertor(URL wsdlLocation, WebServiceFeature... features) {
    super(wsdlLocation, CURRENCYCONVERTOR_QNAME, features);
}public CurrencyConvertor(URL wsdlLocation, QName serviceName) {
    super(wsdlLocation, serviceName);
}public CurrencyConvertor(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
    super(wsdlLocation, serviceName, features);
}
@WebEndpoint(name = "CurrencyConvertorSoap12")
public CurrencyConvertorSoap getCurrencyConvertorSoap12(WebServiceFeature features){
    return super.getPort(new QName("http://www.webserviceX.NET/", "CurrencyConvertorSoap12"), CurrencyConvertorSoap.class, features);
}


public URL __getWsdlLocation(){
    if (CURRENCYCONVERTOR_EXCEPTION != null) {
        throw CURRENCYCONVERTOR_EXCEPTION;
    }
    return CURRENCYCONVERTOR_WSDL_LOCATION;
}


@WebEndpoint(name = "CurrencyConvertorSoap")
public CurrencyConvertorSoap getCurrencyConvertorSoap(WebServiceFeature features){
    return super.getPort(new QName("http://www.webserviceX.NET/", "CurrencyConvertorSoap"), CurrencyConvertorSoap.class, features);
}


@WebEndpoint(name = "CurrencyConvertorHttpGet")
public CurrencyConvertorHttpGet getCurrencyConvertorHttpGet(WebServiceFeature features){
    return super.getPort(new QName("http://www.webserviceX.NET/", "CurrencyConvertorHttpGet"), CurrencyConvertorHttpGet.class, features);
}


@WebEndpoint(name = "CurrencyConvertorHttpPost")
public CurrencyConvertorHttpPost getCurrencyConvertorHttpPost(WebServiceFeature features){
    return super.getPort(new QName("http://www.webserviceX.NET/", "CurrencyConvertorHttpPost"), CurrencyConvertorHttpPost.class, features);
}


}