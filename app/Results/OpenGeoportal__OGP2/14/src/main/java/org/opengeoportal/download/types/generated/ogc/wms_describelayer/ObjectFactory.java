package org.opengeoportal.download.types.generated.ogc.wms_describelayer;
 import javax.xml.bind.annotation.XmlRegistry;
@XmlRegistry
public class ObjectFactory {

/**
 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.ogp
 */
public ObjectFactory() {
}
public Query createQuery(){
    return new Query();
}


public LayerDescription createLayerDescription(){
    return new LayerDescription();
}


public WMSDescribeLayerResponse createWMSDescribeLayerResponse(){
    return new WMSDescribeLayerResponse();
}


}