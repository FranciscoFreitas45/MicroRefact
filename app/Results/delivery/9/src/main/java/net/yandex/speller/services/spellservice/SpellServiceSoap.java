package net.yandex.speller.services.spellservice;
 import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
@WebService(name = "SpellServiceSoap", targetNamespace = "http://speller.yandex.net/services/spellservice")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({ ObjectFactory.class })
public interface SpellServiceSoap {


@WebMethod(action = "http://speller.yandex.net/services/spellservice/checkText")
@WebResult(name = "CheckTextResponse", targetNamespace = "http://speller.yandex.net/services/spellservice", partName = "parameters")
public CheckTextResponse checkText(CheckTextRequest parameters)
;

@WebMethod(action = "http://speller.yandex.net/services/spellservice/checkTexts")
@WebResult(name = "CheckTextsResponse", targetNamespace = "http://speller.yandex.net/services/spellservice", partName = "parameters")
public CheckTextsResponse checkTexts(CheckTextsRequest parameters)
;

}