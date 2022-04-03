package io.delivery.net.webservicex;
 import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
@WebService(name = "GlobalWeatherSoap", targetNamespace = "http://www.webserviceX.NET")
@XmlSeeAlso({ ObjectFactory.class })
public interface GlobalWeatherSoap {


@WebMethod(operationName = "GetWeather", action = "http://www.webserviceX.NET/GetWeather")
@WebResult(name = "GetWeatherResult", targetNamespace = "http://www.webserviceX.NET")
@RequestWrapper(localName = "GetWeather", targetNamespace = "http://www.webserviceX.NET", className = "GetWeather")
@ResponseWrapper(localName = "GetWeatherResponse", targetNamespace = "http://www.webserviceX.NET", className = "GetWeatherResponse")
public String getWeather(String cityName,String countryName)
;

@WebMethod(operationName = "GetCitiesByCountry", action = "http://www.webserviceX.NET/GetCitiesByCountry")
@WebResult(name = "GetCitiesByCountryResult", targetNamespace = "http://www.webserviceX.NET")
@RequestWrapper(localName = "GetCitiesByCountry", targetNamespace = "http://www.webserviceX.NET", className = "GetCitiesByCountry")
@ResponseWrapper(localName = "GetCitiesByCountryResponse", targetNamespace = "http://www.webserviceX.NET", className = "GetCitiesByCountryResponse")
public String getCitiesByCountry(String countryName)
;

}