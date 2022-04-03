package net.webservicex;
 import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
@WebService(name = "CurrencyConvertorHttpPost", targetNamespace = "http://www.webserviceX.NET/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({ ObjectFactory.class })
public interface CurrencyConvertorHttpPost {


@WebMethod(operationName = "ConversionRate")
@WebResult(name = "double", targetNamespace = "http://www.webserviceX.NET/", partName = "Body")
public double conversionRate(String fromCurrency,String toCurrency)
;

}