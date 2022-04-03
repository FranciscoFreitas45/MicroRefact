package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.opengeoportal.Interface.RemoteOWS;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name", "remoteOWS", "layerFeatureConstraints", "userStyle" })
@XmlRootElement(name = "UserLayer")
public class UserLayer {

@XmlElement(name = "Name")
 protected  String name;

@XmlElement(name = "RemoteOWS")
 protected  RemoteOWS remoteOWS;

@XmlElement(name = "LayerFeatureConstraints", required = true)
 protected  LayerFeatureConstraints layerFeatureConstraints;

@XmlElement(name = "UserStyle", required = true)
 protected  List<UserStyle> userStyle;


public void setName(String value){
    this.name = value;
}


public List<UserStyle> getUserStyle(){
    if (userStyle == null) {
        userStyle = new ArrayList<UserStyle>();
    }
    return this.userStyle;
}


public LayerFeatureConstraints getLayerFeatureConstraints(){
    return layerFeatureConstraints;
}


public String getName(){
    return name;
}


public void setLayerFeatureConstraints(LayerFeatureConstraints value){
    this.layerFeatureConstraints = value;
}


public void setRemoteOWS(RemoteOWS value){
    this.remoteOWS = value;
}


public RemoteOWS getRemoteOWS(){
    return remoteOWS;
}


}