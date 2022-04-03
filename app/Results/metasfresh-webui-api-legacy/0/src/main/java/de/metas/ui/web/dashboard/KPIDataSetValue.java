package de.metas.ui.web.dashboard;
 import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
public class KPIDataSetValue {

@JsonProperty("_key")
 private  Object _key;

@JsonIgnore
 private  Map<String,Object> map;


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(map).toString();
}


@JsonIgnore
public void putIfAbsent(String fieldName,Object jsonValue){
    map.putIfAbsent(fieldName, jsonValue);
}


@JsonAnyGetter
public Map<String,Object> getMap(){
    return map;
}


@JsonAnySetter
public void put(String fieldName,Object jsonValue){
    map.put(fieldName, jsonValue);
}


}