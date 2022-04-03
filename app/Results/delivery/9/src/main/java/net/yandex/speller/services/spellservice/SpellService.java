package net.yandex.speller.services.spellservice;
 import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;
@WebServiceClient(name = "SpellService", targetNamespace = "http://speller.yandex.net/services/spellservice", wsdlLocation = "http://speller.yandex.net/services/spellservice?WSDL")
public class SpellService extends Service{

 private  URL SPELLSERVICE_WSDL_LOCATION;

 private  WebServiceException SPELLSERVICE_EXCEPTION;

 private  QName SPELLSERVICE_QNAME;


@WebEndpoint(name = "SpellServiceSoap")
public SpellServiceSoap getSpellServiceSoap(WebServiceFeature features){
    return super.getPort(new QName("http://speller.yandex.net/services/spellservice", "SpellServiceSoap"), SpellServiceSoap.class, features);
}


public URL __getWsdlLocation(){
    if (SPELLSERVICE_EXCEPTION != null) {
        throw SPELLSERVICE_EXCEPTION;
    }
    return SPELLSERVICE_WSDL_LOCATION;
}


}