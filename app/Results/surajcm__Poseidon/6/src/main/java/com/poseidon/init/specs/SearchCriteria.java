package com.poseidon.init.specs;
 import java.io.Serializable;
public class SearchCriteria implements Serializable{

 private  String key;

 private  Object value;

 private  SearchOperation operation;

public SearchCriteria() {
}public SearchCriteria(final String key, final Object value, final SearchOperation operation) {
    this.key = key;
    this.value = value;
    this.operation = operation;
}
public String getKey(){
    return key;
}


public Object getValue(){
    return value;
}


public void setValue(Object value){
    this.value = value;
}


public SearchOperation getOperation(){
    return operation;
}


public void setOperation(SearchOperation operation){
    this.operation = operation;
}


public void setKey(String key){
    this.key = key;
}


}