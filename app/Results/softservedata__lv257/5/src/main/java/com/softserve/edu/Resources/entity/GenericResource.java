package com.softserve.edu.Resources.entity;
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
public void setPropertyValues(Set<PropertyValue> propertyValues){
    this.propertyValues = propertyValues;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((propertyValues == null) ? 0 : propertyValues.hashCode());
    return result;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GenericResource other = (GenericResource) obj;
    if (id != other.id)
        return false;
    if (propertyValues == null) {
        if (other.propertyValues != null)
            return false;
    } else if (!propertyValues.equals(other.propertyValues))
        return false;
    return true;
}


public Set<PropertyValue> getPropertyValues(){
    return propertyValues;
}


public GenericResource setId(int id){
    this.id = id;
    return this;
}


public int getId(){
    return id;
}


@Override
public String toString(){
    return "GenericResource [id=" + id + ", propertyValues=" + propertyValues + "]";
}


}