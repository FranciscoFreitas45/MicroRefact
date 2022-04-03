package com.xwtec.xwserver.util.json.processors;
 import com.xwtec.xwserver.util.json.JSONException;
import com.xwtec.xwserver.util.json.JsonConfig;
public interface JsonValueProcessor {


public Object processArrayValue(Object value,JsonConfig jsonConfig)
;

public Object processObjectValue(String key,Object value,JsonConfig jsonConfig)
;

}