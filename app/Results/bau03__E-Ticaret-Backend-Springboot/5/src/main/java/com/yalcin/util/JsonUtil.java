package com.yalcin.util;
 import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;
public class JsonUtil {


public String buildJsonString(Map<String,Object> mapping){
    String jsonString = new JSONObject().put("timestamp", mapping.get("timestamp").toString()).put("status", mapping.get("status").toString()).put("error", mapping.get("error") == null ? "" : mapping.get("error").toString()).put("message", mapping.get("message") == null ? "" : mapping.get("message").toString()).put("path", mapping.get("path").toString()).toString();
    return jsonString;
}


}