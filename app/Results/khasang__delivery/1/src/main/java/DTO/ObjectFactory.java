package DTO;
 import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
public class ObjectFactory {

 private  QName _GetCustomDutyEventsForMail_QNAME;

 private  QName _GetLanguagesResponse_QNAME;

 private  QName _GetCustomDutyEventsForMailResponse_QNAME;

 private  QName _GetSmsHistory_QNAME;

 private  QName _GetOperationHistoryResponse_QNAME;

 private  QName _GetLanguages_QNAME;

 private  QName _GetOperationHistory_QNAME;

 private  QName _PostalOrderEventsForMail_QNAME;

 private  QName _GetSmsHistoryResponse_QNAME;

 private  QName _PostalOrderEventsForMailResponse_QNAME;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";

/**
 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.russianpost.operationhistory
 */
public ObjectFactory() {
}
@XmlElementDecl(namespace = "http://russianpost.org/operationhistory", name = "getOperationHistory")
public JAXBElement<GetOperationHistory> createGetOperationHistory(GetOperationHistory value){
    return new JAXBElement<GetOperationHistory>(_GetOperationHistory_QNAME, GetOperationHistory.class, null, value);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createGetOperationHistory"))

.queryParam("value",value)
;
JAXBElement<GetOperationHistory> aux = restTemplate.getForObject(builder.toUriString(),JAXBElement<GetOperationHistory>.class);
return aux;
}


}