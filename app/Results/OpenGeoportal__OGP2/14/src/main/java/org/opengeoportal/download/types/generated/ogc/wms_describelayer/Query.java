package org.opengeoportal.download.types.generated.ogc.wms_describelayer;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Query")
public class Query {

@XmlAttribute(required = true)
@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
 protected  String typeName;


public void setTypeName(String value){
    this.typeName = value;
}


public String getTypeName(){
    return typeName;
}


}