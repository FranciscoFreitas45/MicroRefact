package io.delivery.net.webservicex;
 import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
@XmlRegistry
public class ObjectFactory {

 private  QName _String_QNAME;

/**
 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.webservicex
 */
public ObjectFactory() {
}
public GetWeatherResponse createGetWeatherResponse(){
    return new GetWeatherResponse();
}


public GetWeather createGetWeather(){
    return new GetWeather();
}


@XmlElementDecl(namespace = "http://www.webserviceX.NET", name = "string")
public JAXBElement<String> createString(String value){
    return new JAXBElement<String>(_String_QNAME, String.class, null, value);
}


public GetCitiesByCountry createGetCitiesByCountry(){
    return new GetCitiesByCountry();
}


public GetCitiesByCountryResponse createGetCitiesByCountryResponse(){
    return new GetCitiesByCountryResponse();
}


}