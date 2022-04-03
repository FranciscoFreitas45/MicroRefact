package com.xwtec.xwserver.util.json.processors;
 import com.xwtec.xwserver.util.json.JSONException;
import com.xwtec.xwserver.util.json.JSONObject;
import com.xwtec.xwserver.util.json.JsonConfig;
public interface JsonBeanProcessor {


public JSONObject processBean(Object bean,JsonConfig jsonConfig)
;

}