package com.ec.survey.model;
 public class KeyValue {

 private  String key;

 private  String value;

public KeyValue() {
}public KeyValue(String key, String value) {
    this.key = key;
    this.value = value;
}
public String getKey(){
    return key;
}


public String getValue(){
    return value;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((key == null) ? 0 : key.hashCode());
    result = prime * result + ((value == null) ? 0 : value.hashCode());
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
    KeyValue other = (KeyValue) obj;
    if (key == null) {
        if (other.key != null)
            return false;
    } else if (!key.equals(other.key)) {
        return false;
    }
    if (value == null) {
        if (other.value != null)
            return false;
    } else if (!value.equals(other.value)) {
        return false;
    }
    return true;
}


public void setValue(String value){
    this.value = value;
}


public void setKey(String key){
    this.key = key;
}


}