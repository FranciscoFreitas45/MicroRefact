package com.softserve.edu.Resources.DTO;
 import com.softserve.edu.Resources.Constants;
import java.util;
import java.util.stream.Collectors;
public class GenericResource {

 private  int id;

 private  Set<PropertyValue> propertyValues;

// private ResourceType type;
// private Map<ResourceProperty, PropertyValue> resourceValues = new HashMap<>();
public GenericResource() {
}
@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((propertyValues == null) ? 0 : propertyValues.hashCode());
    return result;
}


public Set<PropertyValue> getPropertyValues(){
    return propertyValues;
}


public int getId(){
    return id;
}


}