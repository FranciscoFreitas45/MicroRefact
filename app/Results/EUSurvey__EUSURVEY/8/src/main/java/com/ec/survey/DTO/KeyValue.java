package com.ec.survey.DTO;
 public class KeyValue {

 private  String key;

 private  String value;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";

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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))

.queryParam("obj",obj)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public void setValue(String value){
    this.value = value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setValue"))

.queryParam("value",value)
;
restTemplate.put(builder.toUriString(),null);
}


}