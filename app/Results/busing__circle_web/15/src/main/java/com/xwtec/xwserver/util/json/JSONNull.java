package com.xwtec.xwserver.util.json;
 import java.io.IOException;
import java.io.Writer;
@SuppressWarnings({ "unchecked", "serial" })
public class JSONNull implements JSON{

 private  JSONNull instance;

private JSONNull() {
}
public int size(){
    throw new JSONException("Object is null");
}


public int hashCode(){
    return 37 + "null".hashCode();
}


public boolean equals(Object object){
    return object == null || object == this || object == instance || (object instanceof JSONObject && ((JSONObject) object).isNullObject()) || "null".equals(object);
}


public boolean isEmpty(){
    throw new JSONException("Object is null");
}


public boolean isArray(){
    return false;
}


public String toString(int indentFactor,int indent){
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < indent; i += 1) {
        sb.append(' ');
    }
    sb.append(toString());
    return sb.toString();
}


public JSONNull getInstance(){
    return instance;
}


public Writer write(Writer writer){
    try {
        writer.write(toString());
        return writer;
    } catch (IOException e) {
        throw new JSONException(e);
    }
}


}