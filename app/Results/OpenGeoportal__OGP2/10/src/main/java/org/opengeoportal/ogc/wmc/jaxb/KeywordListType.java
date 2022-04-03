package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeywordListType", namespace = "http://www.opengis.net/context", propOrder = { "keyword" })
public class KeywordListType {

@XmlElement(name = "Keyword", required = true)
 protected  List<String> keyword;


public List<String> getKeyword(){
    if (keyword == null) {
        keyword = new ArrayList<String>();
    }
    return this.keyword;
}


}