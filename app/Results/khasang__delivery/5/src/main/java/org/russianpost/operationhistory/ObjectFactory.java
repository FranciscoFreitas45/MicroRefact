package org.russianpost.operationhistory;
 import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
@XmlRegistry
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

/**
 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.russianpost.operationhistory
 */
public ObjectFactory() {
}
@XmlElementDecl(namespace = "http://russianpost.org/operationhistory", name = "getLanguagesResponse")
public JAXBElement<GetLanguagesResponse> createGetLanguagesResponse(GetLanguagesResponse value){
    return new JAXBElement<GetLanguagesResponse>(_GetLanguagesResponse_QNAME, GetLanguagesResponse.class, null, value);
}


@XmlElementDecl(namespace = "http://russianpost.org/operationhistory", name = "PostalOrderEventsForMailResponse")
public JAXBElement<PostalOrderEventsForMailResponse> createPostalOrderEventsForMailResponse(PostalOrderEventsForMailResponse value){
    return new JAXBElement<PostalOrderEventsForMailResponse>(_PostalOrderEventsForMailResponse_QNAME, PostalOrderEventsForMailResponse.class, null, value);
}


@XmlElementDecl(namespace = "http://russianpost.org/operationhistory", name = "getSmsHistory")
public JAXBElement<GetSmsHistory> createGetSmsHistory(GetSmsHistory value){
    return new JAXBElement<GetSmsHistory>(_GetSmsHistory_QNAME, GetSmsHistory.class, null, value);
}


@XmlElementDecl(namespace = "http://russianpost.org/operationhistory", name = "getSmsHistoryResponse")
public JAXBElement<GetSmsHistoryResponse> createGetSmsHistoryResponse(GetSmsHistoryResponse value){
    return new JAXBElement<GetSmsHistoryResponse>(_GetSmsHistoryResponse_QNAME, GetSmsHistoryResponse.class, null, value);
}


@XmlElementDecl(namespace = "http://russianpost.org/operationhistory", name = "getCustomDutyEventsForMailResponse")
public JAXBElement<GetCustomDutyEventsForMailResponse> createGetCustomDutyEventsForMailResponse(GetCustomDutyEventsForMailResponse value){
    return new JAXBElement<GetCustomDutyEventsForMailResponse>(_GetCustomDutyEventsForMailResponse_QNAME, GetCustomDutyEventsForMailResponse.class, null, value);
}


@XmlElementDecl(namespace = "http://russianpost.org/operationhistory", name = "getCustomDutyEventsForMail")
public JAXBElement<GetCustomDutyEventsForMail> createGetCustomDutyEventsForMail(GetCustomDutyEventsForMail value){
    return new JAXBElement<GetCustomDutyEventsForMail>(_GetCustomDutyEventsForMail_QNAME, GetCustomDutyEventsForMail.class, null, value);
}


@XmlElementDecl(namespace = "http://russianpost.org/operationhistory", name = "getOperationHistory")
public JAXBElement<GetOperationHistory> createGetOperationHistory(GetOperationHistory value){
    return new JAXBElement<GetOperationHistory>(_GetOperationHistory_QNAME, GetOperationHistory.class, null, value);
}


@XmlElementDecl(namespace = "http://russianpost.org/operationhistory", name = "getLanguages")
public JAXBElement<GetLanguages> createGetLanguages(GetLanguages value){
    return new JAXBElement<GetLanguages>(_GetLanguages_QNAME, GetLanguages.class, null, value);
}


@XmlElementDecl(namespace = "http://russianpost.org/operationhistory", name = "PostalOrderEventsForMail")
public JAXBElement<PostalOrderEventsForMail> createPostalOrderEventsForMail(PostalOrderEventsForMail value){
    return new JAXBElement<PostalOrderEventsForMail>(_PostalOrderEventsForMail_QNAME, PostalOrderEventsForMail.class, null, value);
}


@XmlElementDecl(namespace = "http://russianpost.org/operationhistory", name = "getOperationHistoryResponse")
public JAXBElement<GetOperationHistoryResponse> createGetOperationHistoryResponse(GetOperationHistoryResponse value){
    return new JAXBElement<GetOperationHistoryResponse>(_GetOperationHistoryResponse_QNAME, GetOperationHistoryResponse.class, null, value);
}


}