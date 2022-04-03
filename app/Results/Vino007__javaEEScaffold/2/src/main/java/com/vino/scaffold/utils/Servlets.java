package com.vino.scaffold.utils;
 import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.servlet.ServletRequest;
import org.apache.commons.lang3.Validate;
public class Servlets {


public Map<String,Object> getParametersStartingWith(ServletRequest request,String prefix){
    Validate.notNull(request, "Request must not be null");
    Enumeration paramNames = request.getParameterNames();
    Map<String, Object> params = new TreeMap<String, Object>();
    if (prefix == null) {
        prefix = "";
    }
    while ((paramNames != null) && paramNames.hasMoreElements()) {
        String paramName = (String) paramNames.nextElement();
        if ("".equals(prefix) || paramName.startsWith(prefix)) {
            String unprefixed = paramName.substring(prefix.length());
            String[] values = request.getParameterValues(paramName);
            if ((values == null) || (values.length == 0)) {
            // Do nothing, no values found at all.
            } else if (values.length > 1) {
                params.put(unprefixed, values);
            } else {
                params.put(unprefixed, values[0]);
            }
        }
    }
    return params;
}


public String encodeParameterStringWithPrefix(Map<String,Object> params,String prefix){
    if (params.isEmpty()) {
        return "";
    }
    if (prefix == null) {
        prefix = "";
    }
    StringBuilder queryStringBuilder = new StringBuilder();
    Iterator<Entry<String, Object>> it = params.entrySet().iterator();
    while (it.hasNext()) {
        Entry<String, Object> entry = it.next();
        queryStringBuilder.append(prefix).append(entry.getKey()).append('=').append(entry.getValue());
        if (it.hasNext()) {
            queryStringBuilder.append('&');
        }
    }
    return queryStringBuilder.toString();
}


}