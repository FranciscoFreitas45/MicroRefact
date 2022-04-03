package org.russianpost.sms_info.data;
 import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
@XmlRegistry
public class ObjectFactory {

 private  QName _SmsHistoryFaultReason_QNAME;

 private  QName _LanguageFaultReason_QNAME;

 private  QName _AuthorisationFaultReason_QNAME;

/**
 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.russianpost.sms_info.data
 */
public ObjectFactory() {
}
@XmlElementDecl(namespace = "http://russianpost.org/sms-info/data", name = "SmsHistoryFaultReason")
public JAXBElement<String> createSmsHistoryFaultReason(String value){
    return new JAXBElement<String>(_SmsHistoryFaultReason_QNAME, String.class, null, value);
}


public SmsHistoryRequest createSmsHistoryRequest(){
    return new SmsHistoryRequest();
}


public SmsHistoryData createSmsHistoryData(){
    return new SmsHistoryData();
}


public SmsHistoryRecord createSmsHistoryRecord(){
    return new SmsHistoryRecord();
}


public LanguageData.Language createLanguageDataLanguage(){
    return new LanguageData.Language();
}


@XmlElementDecl(namespace = "http://russianpost.org/sms-info/data", name = "AuthorisationFaultReason")
public JAXBElement<String> createAuthorisationFaultReason(String value){
    return new JAXBElement<String>(_AuthorisationFaultReason_QNAME, String.class, null, value);
}


public LanguageData createLanguageData(){
    return new LanguageData();
}


@XmlElementDecl(namespace = "http://russianpost.org/sms-info/data", name = "LanguageFaultReason")
public JAXBElement<String> createLanguageFaultReason(String value){
    return new JAXBElement<String>(_LanguageFaultReason_QNAME, String.class, null, value);
}


}