package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactInformationType", namespace = "http://www.opengis.net/context", propOrder = { "contactPersonPrimary", "contactPosition", "contactAddress", "contactVoiceTelephone", "contactFacsimileTelephone", "contactElectronicMailAddress" })
public class ContactInformationType {

@XmlElement(name = "ContactPersonPrimary")
 protected  ContactPersonPrimaryType contactPersonPrimary;

@XmlElement(name = "ContactPosition")
 protected  String contactPosition;

@XmlElement(name = "ContactAddress")
 protected  AddressType contactAddress;

@XmlElement(name = "ContactVoiceTelephone")
 protected  String contactVoiceTelephone;

@XmlElement(name = "ContactFacsimileTelephone")
 protected  String contactFacsimileTelephone;

@XmlElement(name = "ContactElectronicMailAddress")
 protected  String contactElectronicMailAddress;


public void setContactPosition(String value){
    this.contactPosition = value;
}


public void setContactElectronicMailAddress(String value){
    this.contactElectronicMailAddress = value;
}


public void setContactPersonPrimary(ContactPersonPrimaryType value){
    this.contactPersonPrimary = value;
}


public String getContactVoiceTelephone(){
    return contactVoiceTelephone;
}


public ContactPersonPrimaryType getContactPersonPrimary(){
    return contactPersonPrimary;
}


public void setContactAddress(AddressType value){
    this.contactAddress = value;
}


public String getContactPosition(){
    return contactPosition;
}


public void setContactVoiceTelephone(String value){
    this.contactVoiceTelephone = value;
}


public String getContactFacsimileTelephone(){
    return contactFacsimileTelephone;
}


public void setContactFacsimileTelephone(String value){
    this.contactFacsimileTelephone = value;
}


public String getContactElectronicMailAddress(){
    return contactElectronicMailAddress;
}


public AddressType getContactAddress(){
    return contactAddress;
}


}