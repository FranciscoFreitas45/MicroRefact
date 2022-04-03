package com.xwtec.xwserver.util.json;
 import com.xwtec.xwserver.util.json.util.JSONTokener;
import com.xwtec.xwserver.util.json.util.JSONUtils;
@SuppressWarnings("unchecked")
public class JSONSerializer {


public JSON toJSON(String string,JsonConfig jsonConfig){
    JSON json = null;
    if (string.startsWith("[")) {
        json = JSONArray.fromObject(string, jsonConfig);
    } else if (string.startsWith("{")) {
        json = JSONObject.fromObject(string, jsonConfig);
    } else if ("null".equals(string)) {
        json = JSONNull.getInstance();
    } else {
        throw new JSONException("Invalid JSON String");
    }
    return json;
}


public Object toJava(JSON json,JsonConfig jsonConfig){
    if (JSONUtils.isNull(json)) {
        return null;
    }
    Object object = null;
    if (json instanceof JSONArray) {
        if (jsonConfig.getArrayMode() == JsonConfig.MODE_OBJECT_ARRAY) {
            object = JSONArray.toArray((JSONArray) json, jsonConfig);
        } else {
            object = JSONArray.toCollection((JSONArray) json, jsonConfig);
        }
    } else {
        object = JSONObject.toBean((JSONObject) json, jsonConfig);
    }
    return object;
}


}