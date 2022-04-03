package com.gbcom.system.domain.bean.column;
 import javax.xml.bind.annotation.XmlRegistry;
@XmlRegistry
public class ObjectFactory {

/**
 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example
 */
public ObjectFactory() {
}
public ColumnDefinition createColumnDefinition(){
    return new ColumnDefinition();
}


public ColumnParam createColumnParam(){
    return new ColumnParam();
}


}