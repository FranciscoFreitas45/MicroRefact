package com.DTO;
 import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.xwtec.xwserver.util.json.util.JSONUtils;
public class JSONFunction implements Serializable{

 private  String[] EMPTY_PARAM_ARRAY;

 private  String[] params;

 private  String text;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://15";

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


public String[] getParams(){
    return params;
}


public JSONFunction parse(String str){
    if (!JSONUtils.isFunction(str)) {
        throw new JSONException("String is not a function. " + str);
    } else {
        String params = JSONUtils.getFunctionParams(str);
        String text = JSONUtils.getFunctionBody(str);
        return new JSONFunction((params != null) ? StringUtils.split(params, ",") : null, text != null ? text : "");
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/parse"))

.queryParam("str",str)
;
JSONFunction aux = restTemplate.getForObject(builder.toUriString(),JSONFunction.class);
return aux;
}


}