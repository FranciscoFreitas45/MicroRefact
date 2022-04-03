package net.webservicex;
 import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
@WebService(name = "CurrencyConvertorSoap", targetNamespace = "http://www.webserviceX.NET/")
@XmlSeeAlso({ ObjectFactory.class })
public interface CurrencyConvertorSoap {


@WebMethod(operationName = "ConversionRate", action = "http://www.webserviceX.NET/ConversionRate")
@WebResult(name = "ConversionRateResult", targetNamespace = "http://www.webserviceX.NET/")
@RequestWrapper(localName = "ConversionRate", targetNamespace = "http://www.webserviceX.NET/", className = "net.webservicex.ConversionRate")
@ResponseWrapper(localName = "ConversionRateResponse", targetNamespace = "http://www.webserviceX.NET/", className = "net.webservicex.ConversionRateResponse")
public double conversionRate(Currency fromCurrency,Currency toCurrency)
;

}