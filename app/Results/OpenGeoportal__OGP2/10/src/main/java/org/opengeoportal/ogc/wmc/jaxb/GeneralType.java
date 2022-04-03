package org.opengeoportal.ogc.wmc.jaxb;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.opengeoportal.Interface.ContactInformationType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeneralType", namespace = "http://www.opengis.net/context", propOrder = { "window", "boundingBox", "title", "keywordList", "_abstract", "logoURL", "descriptionURL", "contactInformation", "extension" })
public class GeneralType {

@XmlElement(name = "Window")
 protected  WindowType window;

@XmlElement(name = "BoundingBox", required = true)
 protected  BoundingBoxType boundingBox;

@XmlElement(name = "Title", required = true)
 protected  String title;

@XmlElement(name = "KeywordList")
 protected  KeywordListType keywordList;

@XmlElement(name = "Abstract")
 protected  String _abstract;

@XmlElement(name = "LogoURL")
 protected  URLType logoURL;

@XmlElement(name = "DescriptionURL")
 protected  URLType descriptionURL;

@XmlElement(name = "ContactInformation")
 protected  ContactInformationType contactInformation;

@XmlElement(name = "Extension")
 protected  ExtensionType extension;


public ExtensionType getExtension(){
    return extension;
}


public void setExtension(ExtensionType value){
    this.extension = value;
}


public WindowType getWindow(){
    return window;
}


public void setBoundingBox(BoundingBoxType value){
    this.boundingBox = value;
}


public void setTitle(String value){
    this.title = value;
}


public void setContactInformation(ContactInformationType value){
    this.contactInformation = value;
}


public void setWindow(WindowType value){
    this.window = value;
}


public void setLogoURL(URLType value){
    this.logoURL = value;
}


public URLType getDescriptionURL(){
    return descriptionURL;
}


public void setDescriptionURL(URLType value){
    this.descriptionURL = value;
}


public BoundingBoxType getBoundingBox(){
    return boundingBox;
}


public String getTitle(){
    return title;
}


public void setKeywordList(KeywordListType value){
    this.keywordList = value;
}


public void setAbstract(String value){
    this._abstract = value;
}


public URLType getLogoURL(){
    return logoURL;
}


public ContactInformationType getContactInformation(){
    return contactInformation;
}


public String getAbstract(){
    return _abstract;
}


public KeywordListType getKeywordList(){
    return keywordList;
}


}