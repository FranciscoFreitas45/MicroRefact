package com.gbcom.system.domain.bean.param;
 import javax.xml.bind.annotation.XmlRegistry;
@XmlRegistry
public class ObjectFactory {

/**
 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hc.ipromis.core.param
 */
public ObjectFactory() {
}
public Constraint createConstraint(){
    return new Constraint();
}


}