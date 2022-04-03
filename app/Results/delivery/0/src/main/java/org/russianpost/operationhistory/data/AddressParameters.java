package org.russianpost.operationhistory.data;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressParameters", propOrder = { "destinationAddress", "operationAddress", "mailDirect", "countryFrom", "countryOper" })
public class AddressParameters {

@XmlElement(name = "DestinationAddress")
 protected  Address destinationAddress;

@XmlElement(name = "OperationAddress", required = true)
 protected  Address operationAddress;

@XmlElement(name = "MailDirect")
 protected  Country mailDirect;

@XmlElement(name = "CountryFrom")
 protected  Country countryFrom;

@XmlElement(name = "CountryOper", required = true)
 protected  Country countryOper;


public Country getMailDirect(){
    return mailDirect;
}


public void setCountryOper(Country value){
    this.countryOper = value;
}


public void setDestinationAddress(Address value){
    this.destinationAddress = value;
}


public void setOperationAddress(Address value){
    this.operationAddress = value;
}


public Country getCountryFrom(){
    return countryFrom;
}


public void setMailDirect(Country value){
    this.mailDirect = value;
}


public void setCountryFrom(Country value){
    this.countryFrom = value;
}


public Address getDestinationAddress(){
    return destinationAddress;
}


public Address getOperationAddress(){
    return operationAddress;
}


public Country getCountryOper(){
    return countryOper;
}


}