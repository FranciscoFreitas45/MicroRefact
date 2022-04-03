package com.softserve.edu.Resources.entity;
 import com.softserve.edu.Resources.Interface.ConstrainedProperty;
public class PropertyValue implements Comparable<PropertyValue>{

 private  int id;

 private  ConstrainedProperty type;

 private  String value;

public PropertyValue() {
}public PropertyValue(ConstrainedProperty type) {
    this.type = type;
}public PropertyValue(ConstrainedProperty type, String value) {
    this(type);
    setValue(value);
}
public String getValue(){
    return value;
}


public ConstrainedProperty getType(){
    return type;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    return result;
}


public PropertyValue setValue(String value){
    if (!type.isRequired() && value == null) {
        throw new NullPointerException("NOT NULL constraint violation.");
    }
    // if (!validate(value)) {
    // throw new IllegalArgumentException(String.format("Invalid value format. Should match \"%s\"",
    // type.getProperty().getPattern()));
    // }
    this.value = value;
    return this;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    PropertyValue other = (PropertyValue) obj;
    if (id != other.id)
        return false;
    if (type == null) {
        if (other.type != null)
            return false;
    } else if (!type.equals(other.type))
        return false;
    if (value == null) {
        if (other.value != null)
            return false;
    } else if (!value.equals(other.value))
        return false;
    return true;
}


@Override
public String toString(){
    return "PropertyValue [type=" + type + ", value=" + value + "]";
}


@Override
public int compareTo(PropertyValue other){
    ResourceProperty otherProperty = other.getType().getProperty();
    int typeCmp = getType().getProperty().getTitle().compareTo(otherProperty.getTitle());
    if (typeCmp == 0) {
        ValueType valueType = getType().getProperty().getValueType();
        return ValueType.compare(valueType, getValue(), other.getValue());
    }
    return typeCmp;
}


public PropertyValue setType(ConstrainedProperty type){
    this.type = type;
    return this;
}


public boolean validate(String value){
    return ((type.getProperty().getPattern() != null) && (value != null)) && value.matches(type.getProperty().getPattern());
}


}