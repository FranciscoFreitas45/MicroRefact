package net.webservicex;
 import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
@WebService(name = "countrySoap", targetNamespace = "http://www.webserviceX.NET")
@XmlSeeAlso({ ObjectFactory.class })
public interface CountrySoap {


@WebMethod(operationName = "GetCountryByCountryCode", action = "http://www.webserviceX.NET/GetCountryByCountryCode")
@WebResult(name = "GetCountryByCountryCodeResult", targetNamespace = "http://www.webserviceX.NET")
@RequestWrapper(localName = "GetCountryByCountryCode", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetCountryByCountryCode")
@ResponseWrapper(localName = "GetCountryByCountryCodeResponse", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetCountryByCountryCodeResponse")
public String getCountryByCountryCode(String countryCode)
;

@WebMethod(operationName = "GetCurrencyByCountry", action = "http://www.webserviceX.NET/GetCurrencyByCountry")
@WebResult(name = "GetCurrencyByCountryResult", targetNamespace = "http://www.webserviceX.NET")
@RequestWrapper(localName = "GetCurrencyByCountry", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetCurrencyByCountry")
@ResponseWrapper(localName = "GetCurrencyByCountryResponse", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetCurrencyByCountryResponse")
public String getCurrencyByCountry(String countryName)
;

@WebMethod(operationName = "GetCurrencyCode", action = "http://www.webserviceX.NET/GetCurrencyCode")
@WebResult(name = "GetCurrencyCodeResult", targetNamespace = "http://www.webserviceX.NET")
@RequestWrapper(localName = "GetCurrencyCode", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetCurrencyCode")
@ResponseWrapper(localName = "GetCurrencyCodeResponse", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetCurrencyCodeResponse")
public String getCurrencyCode()
;

@WebMethod(operationName = "GetCountries", action = "http://www.webserviceX.NET/GetCountries")
@WebResult(name = "GetCountriesResult", targetNamespace = "http://www.webserviceX.NET")
@RequestWrapper(localName = "GetCountries", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetCountries")
@ResponseWrapper(localName = "GetCountriesResponse", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetCountriesResponse")
public String getCountries()
;

@WebMethod(operationName = "GetCurrencies", action = "http://www.webserviceX.NET/GetCurrencies")
@WebResult(name = "GetCurrenciesResult", targetNamespace = "http://www.webserviceX.NET")
@RequestWrapper(localName = "GetCurrencies", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetCurrencies")
@ResponseWrapper(localName = "GetCurrenciesResponse", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetCurrenciesResponse")
public String getCurrencies()
;

@WebMethod(operationName = "GetCurrencyCodeByCurrencyName", action = "http://www.webserviceX.NET/GetCurrencyCodeByCurrencyName")
@WebResult(name = "GetCurrencyCodeByCurrencyNameResult", targetNamespace = "http://www.webserviceX.NET")
@RequestWrapper(localName = "GetCurrencyCodeByCurrencyName", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetCurrencyCodeByCurrencyName")
@ResponseWrapper(localName = "GetCurrencyCodeByCurrencyNameResponse", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetCurrencyCodeByCurrencyNameResponse")
public String getCurrencyCodeByCurrencyName(String currencyName)
;

@WebMethod(operationName = "GetISOCountryCodeByCountyName", action = "http://www.webserviceX.NET/GetISOCountryCodeByCountyName")
@WebResult(name = "GetISOCountryCodeByCountyNameResult", targetNamespace = "http://www.webserviceX.NET")
@RequestWrapper(localName = "GetISOCountryCodeByCountyName", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetISOCountryCodeByCountyName")
@ResponseWrapper(localName = "GetISOCountryCodeByCountyNameResponse", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetISOCountryCodeByCountyNameResponse")
public String getISOCountryCodeByCountyName(String countryName)
;

@WebMethod(operationName = "GetGMTbyCountry", action = "http://www.webserviceX.NET/GetGMTbyCountry")
@WebResult(name = "GetGMTbyCountryResult", targetNamespace = "http://www.webserviceX.NET")
@RequestWrapper(localName = "GetGMTbyCountry", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetGMTbyCountry")
@ResponseWrapper(localName = "GetGMTbyCountryResponse", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetGMTbyCountryResponse")
public String getGMTbyCountry(String countryName)
;

@WebMethod(operationName = "GetISD", action = "http://www.webserviceX.NET/GetISD")
@WebResult(name = "GetISDResult", targetNamespace = "http://www.webserviceX.NET")
@RequestWrapper(localName = "GetISD", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetISD")
@ResponseWrapper(localName = "GetISDResponse", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetISDResponse")
public String getISD(String countryName)
;

@WebMethod(operationName = "GetCountryByCurrencyCode", action = "http://www.webserviceX.NET/GetCountryByCurrencyCode")
@WebResult(name = "GetCountryByCurrencyCodeResult", targetNamespace = "http://www.webserviceX.NET")
@RequestWrapper(localName = "GetCountryByCurrencyCode", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetCountryByCurrencyCode")
@ResponseWrapper(localName = "GetCountryByCurrencyCodeResponse", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetCountryByCurrencyCodeResponse")
public String getCountryByCurrencyCode(String currencyCode)
;

}