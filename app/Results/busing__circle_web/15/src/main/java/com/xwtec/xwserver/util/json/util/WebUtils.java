package com.xwtec.xwserver.util.json.util;
 import java.util.Iterator;
import com.xwtec.xwserver.util.json.JSON;
import com.xwtec.xwserver.util.json.JSONArray;
import com.xwtec.xwserver.util.json.JSONNull;
import com.xwtec.xwserver.util.json.JSONObject;
import com.Interface.WebHijackPreventionStrategy;
import com.Interface.WebHijackPreventionStrategy;
@SuppressWarnings("unchecked")
public class WebUtils {

 private  WebHijackPreventionStrategy DEFAULT_WEB_HIJACK_PREVENTION_STRATEGY;

 private  WebHijackPreventionStrategy webHijackPreventionStrategy;

private WebUtils() {
}
public String quote(String str){
    if (str.indexOf(" ") > -1 || str.indexOf(":") > -1) {
        return JSONUtils.quote(str);
    } else {
        return str;
    }
}


public WebHijackPreventionStrategy getWebHijackPreventionStrategy(){
    return webHijackPreventionStrategy;
}


public void setWebHijackPreventionStrategy(WebHijackPreventionStrategy strategy){
    webHijackPreventionStrategy = strategy == null ? DEFAULT_WEB_HIJACK_PREVENTION_STRATEGY : strategy;
}


public String toString(Object object){
    if (object instanceof JSON) {
        return toString((JSON) object);
    } else {
        return JSONUtils.valueToString(object);
    }
}


public String protect(JSON json,boolean shrink){
    String output = !shrink ? json.toString(0) : toString(json);
    return webHijackPreventionStrategy.protect(output);
}


public String join(JSONArray jsonArray){
    int len = jsonArray.size();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < len; i += 1) {
        if (i > 0) {
            sb.append(",");
        }
        Object value = jsonArray.get(i);
        sb.append(toString(value));
    }
    return sb.toString();
}


}