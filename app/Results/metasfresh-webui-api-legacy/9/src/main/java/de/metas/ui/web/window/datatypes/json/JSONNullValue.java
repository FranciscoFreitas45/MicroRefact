package de.metas.ui.web.window.datatypes.json;
 import javax.annotation.Nullable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
@ApiModel("null-value")
@JsonSerialize(using = JSONNullValueSerializer.class)
public class JSONNullValue {

 public  JSONNullValue instance;


public Object toNullIfInstance(Object jsonValueObj){
    if (jsonValueObj instanceof JSONNullValue) {
        return null;
    }
    return jsonValueObj;
}


public Object wrapIfNull(Object value){
    return value == null ? instance : value;
}


public boolean isNull(Object value){
    return value == null || value instanceof JSONNullValue;
}


@Override
public String toString(){
    return "null";
}


}