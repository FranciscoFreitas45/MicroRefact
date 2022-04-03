package org.russianpost.operationhistory.data;
 import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
@XmlRegistry
public class ObjectFactory {

 private  QName _LanguageFaultReason_QNAME;

 private  QName _UpdateOperationRequest_QNAME;

 private  QName _AuthorizationFaultReason_QNAME;

 private  QName _OperationHistoryFaultReason_QNAME;


public AddressParameters createAddressParameters(){
    return new AddressParameters();
}


public Country createCountry(){
    return new Country();
}


public Address createAddress(){
    return new Address();
}


public LanguageData.Language createLanguageDataLanguage(){
    return new LanguageData.Language();
}


@XmlElementDecl(namespace = "http://russianpost.org/operationhistory/data", name = "OperationHistoryFaultReason")
public JAXBElement<String> createOperationHistoryFaultReason(String value){
    return new JAXBElement<String>(_OperationHistoryFaultReason_QNAME, String.class, null, value);
}


@XmlElementDecl(namespace = "http://russianpost.org/operationhistory/data", name = "AuthorizationFaultReason")
public JAXBElement<String> createAuthorizationFaultReason(String value){
    return new JAXBElement<String>(_AuthorizationFaultReason_QNAME, String.class, null, value);
}


public AuthorizationHeader createAuthorizationHeader(){
    return new AuthorizationHeader();
}


public OperationHistoryRecord createOperationHistoryRecord(){
    return new OperationHistoryRecord();
}


public FinanceParameters createFinanceParameters(){
    return new FinanceParameters();
}


public OperationParameters createOperationParameters(){
    return new OperationParameters();
}


public UserParameters createUserParameters(){
    return new UserParameters();
}


public OperationHistoryData createOperationHistoryData(){
    return new OperationHistoryData();
}


@XmlElementDecl(namespace = "http://russianpost.org/operationhistory/data", name = "UpdateOperationRequest")
public JAXBElement<UpdateOperationRequest> createUpdateOperationRequest(UpdateOperationRequest value){
    return new JAXBElement<UpdateOperationRequest>(_UpdateOperationRequest_QNAME, UpdateOperationRequest.class, null, value);
}


public Rtm02Parameter createRtm02Parameter(){
    return new Rtm02Parameter();
}


public LanguageData createLanguageData(){
    return new LanguageData();
}


public ItemParameters createItemParameters(){
    return new ItemParameters();
}


public OperationHistoryRequest createOperationHistoryRequest(){
    return new OperationHistoryRequest();
}


@XmlElementDecl(namespace = "http://russianpost.org/operationhistory/data", name = "LanguageFaultReason")
public JAXBElement<String> createLanguageFaultReason(String value){
    return new JAXBElement<String>(_LanguageFaultReason_QNAME, String.class, null, value);
}


}