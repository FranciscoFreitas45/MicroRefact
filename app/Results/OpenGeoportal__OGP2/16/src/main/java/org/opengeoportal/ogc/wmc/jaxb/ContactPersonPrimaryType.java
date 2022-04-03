package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactPersonPrimaryType", namespace = "http://www.opengis.net/context", propOrder = { "contactPerson", "contactOrganization" })
public class ContactPersonPrimaryType {

@XmlElement(name = "ContactPerson")
 protected  String contactPerson;

@XmlElement(name = "ContactOrganization")
 protected  String contactOrganization;


public void setContactPerson(String value){
    this.contactPerson = value;
}


public String getContactPerson(){
    return contactPerson;
}


public void setContactOrganization(String value){
    this.contactOrganization = value;
}


public String getContactOrganization(){
    return contactOrganization;
}


}