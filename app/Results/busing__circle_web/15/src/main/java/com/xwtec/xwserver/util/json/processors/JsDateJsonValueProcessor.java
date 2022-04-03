package com.xwtec.xwserver.util.json.processors;
 import com.xwtec.xwserver.util.json.JsonConfig;
public class JsDateJsonValueProcessor implements JsonValueProcessor{

 private  JsonBeanProcessor processor;

public JsDateJsonValueProcessor() {
    processor = new JsDateJsonBeanProcessor();
}
public Object process(Object value,JsonConfig jsonConfig){
    return processor.processBean(value, jsonConfig);
}


public Object processArrayValue(Object value,JsonConfig jsonConfig){
    return process(value, jsonConfig);
}


public Object processObjectValue(String key,Object value,JsonConfig jsonConfig){
    return process(value, jsonConfig);
}


}