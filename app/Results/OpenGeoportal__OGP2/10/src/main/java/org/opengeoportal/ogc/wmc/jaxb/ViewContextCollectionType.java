package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewContextCollectionType", namespace = "http://www.opengis.net/context", propOrder = { "viewContextReference" })
public class ViewContextCollectionType {

@XmlElement(name = "ViewContextReference", required = true)
 protected  List<ViewContextReferenceType> viewContextReference;

@XmlAttribute(required = true)
 protected  String version;


public String getVersion(){
    if (version == null) {
        return "1.1.0";
    } else {
        return version;
    }
}


public List<ViewContextReferenceType> getViewContextReference(){
    if (viewContextReference == null) {
        viewContextReference = new ArrayList<ViewContextReferenceType>();
    }
    return this.viewContextReference;
}


public void setVersion(String value){
    this.version = value;
}


}