package com.lingxiang2014.DTO;
 import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
public class Filter implements Serializable{

 private  long serialVersionUID;

 private  boolean DEFAULT_IGNORE_CASE;

 private  String property;

 private  Operator operator;

 private  Object value;

 private  Boolean ignoreCase;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

public Filter() {
}public Filter(String property, Operator operator, Object value) {
    this.property = property;
    this.operator = operator;
    this.value = value;
}public Filter(String property, Operator operator, Object value, boolean ignoreCase) {
    this.property = property;
    this.operator = operator;
    this.value = value;
    this.ignoreCase = ignoreCase;
}
public String getProperty(){
    return property;
}


public Boolean getIgnoreCase(){
    return ignoreCase;
}


public Object getValue(){
    return value;
}


public Operator getOperator(){
    return operator;
}


public Filter eq(String property,Object value,boolean ignoreCase){
    return new Filter(property, Operator.eq, value, ignoreCase);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/eq"))

.queryParam("property",property)
.queryParam("value",value)
.queryParam("ignoreCase",ignoreCase)
;
Filter aux = restTemplate.getForObject(builder.toUriString(),Filter.class);
return aux;
}


}