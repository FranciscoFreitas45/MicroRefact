package com.xwtec.xwserver.util.json;
 import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.xwtec.xwserver.util.json.util.JSONUtils;
@SuppressWarnings({ "unchecked", "serial" })
public class JSONFunction implements Serializable{

 private  String[] EMPTY_PARAM_ARRAY;

 private  String[] params;

 private  String text;

/**
 * Constructs a JSONFunction with no parameters.
 *
 * @param text The text of the function
 */
public JSONFunction(String text) {
    this(null, text);
}/**
 * Constructs a JSONFunction with parameters.
 *
 * @param params The parameters of the function
 * @param text The text of the function
 */
public JSONFunction(String[] params, String text) {
    this.text = (text != null) ? text.trim() : "";
    if (params != null) {
        if (params.length == 1 && params[0].trim().equals("")) {
            this.params = EMPTY_PARAM_ARRAY;
        } else {
            this.params = new String[params.length];
            System.arraycopy(params, 0, this.params, 0, params.length);
            // remove empty spaces
            for (int i = 0; i < params.length; i++) {
                this.params[i] = this.params[i].trim();
            }
        }
    } else {
        this.params = EMPTY_PARAM_ARRAY;
    }
}
public String getText(){
    return text;
}


public int hashCode(){
    HashCodeBuilder builder = new HashCodeBuilder();
    for (int i = 0; i < params.length; i++) {
        builder.append(params[i]);
    }
    builder.append(text);
    return builder.toHashCode();
}


public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (obj instanceof String) {
        try {
            JSONFunction other = parse((String) obj);
            return equals(other);
        } catch (JSONException e) {
            return false;
        }
    }
    if (!(obj instanceof JSONFunction)) {
        return false;
    }
    JSONFunction other = (JSONFunction) obj;
    if (params.length != other.params.length) {
        return false;
    }
    EqualsBuilder builder = new EqualsBuilder();
    for (int i = 0; i < params.length; i++) {
        builder.append(params[i], other.params[i]);
    }
    builder.append(text, other.text);
    return builder.isEquals();
}


public String toString(){
    StringBuffer b = new StringBuffer("function(");
    if (params.length > 0) {
        for (int i = 0; i < params.length - 1; i++) {
            b.append(params[i]).append(',');
        }
        b.append(params[params.length - 1]);
    }
    b.append("){");
    if (text.length() > 0) {
        b.append(' ').append(text).append(' ');
    }
    b.append('}');
    return b.toString();
}


public JSONFunction parse(String str){
    if (!JSONUtils.isFunction(str)) {
        throw new JSONException("String is not a function. " + str);
    } else {
        String params = JSONUtils.getFunctionParams(str);
        String text = JSONUtils.getFunctionBody(str);
        return new JSONFunction((params != null) ? StringUtils.split(params, ",") : null, text != null ? text : "");
    }
}


public String[] getParams(){
    return params;
}


}