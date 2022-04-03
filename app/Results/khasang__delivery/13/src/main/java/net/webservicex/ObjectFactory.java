package net.webservicex;
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
public GetCurrencyCodeByCurrencyNameResponse createGetCurrencyCodeByCurrencyNameResponse(){
    return new GetCurrencyCodeByCurrencyNameResponse();
}


public GetISDResponse createGetISDResponse(){
    return new GetISDResponse();
}


public GetGMTbyCountryResponse createGetGMTbyCountryResponse(){
    return new GetGMTbyCountryResponse();
}


public GetGMTbyCountry createGetGMTbyCountry(){
    return new GetGMTbyCountry();
}


public GetCountryByCountryCodeResponse createGetCountryByCountryCodeResponse(){
    return new GetCountryByCountryCodeResponse();
}


public GetCurrenciesResponse createGetCurrenciesResponse(){
    return new GetCurrenciesResponse();
}


@XmlElementDecl(namespace = "http://www.webserviceX.NET", name = "string")
public JAXBElement<String> createString(String value){
    return new JAXBElement<String>(_String_QNAME, String.class, null, value);
}


public GetCountryByCurrencyCode createGetCountryByCurrencyCode(){
    return new GetCountryByCurrencyCode();
}


public GetCurrencies createGetCurrencies(){
    return new GetCurrencies();
}


public GetCountriesResponse createGetCountriesResponse(){
    return new GetCountriesResponse();
}


public ConversionRateResponse createConversionRateResponse(){
    return new ConversionRateResponse();
}


public GetCurrencyCodeByCurrencyName createGetCurrencyCodeByCurrencyName(){
    return new GetCurrencyCodeByCurrencyName();
}


public GetCurrencyCodeResponse createGetCurrencyCodeResponse(){
    return new GetCurrencyCodeResponse();
}


public GetCountries createGetCountries(){
    return new GetCountries();
}


public GetCurrencyByCountry createGetCurrencyByCountry(){
    return new GetCurrencyByCountry();
}


public GetISOCountryCodeByCountyNameResponse createGetISOCountryCodeByCountyNameResponse(){
    return new GetISOCountryCodeByCountyNameResponse();
}


public GetCountryByCurrencyCodeResponse createGetCountryByCurrencyCodeResponse(){
    return new GetCountryByCurrencyCodeResponse();
}


public GetISOCountryCodeByCountyName createGetISOCountryCodeByCountyName(){
    return new GetISOCountryCodeByCountyName();
}


public GetCountryByCountryCode createGetCountryByCountryCode(){
    return new GetCountryByCountryCode();
}


public GetCurrencyByCountryResponse createGetCurrencyByCountryResponse(){
    return new GetCurrencyByCountryResponse();
}


public GetISD createGetISD(){
    return new GetISD();
}


public GetCurrencyCode createGetCurrencyCode(){
    return new GetCurrencyCode();
}


public ConversionRate createConversionRate(){
    return new ConversionRate();
}


}