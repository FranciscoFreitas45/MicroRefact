package com.gbcom.system.utils;
 import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;
public class JsonUtil {

 private  Logger logger;


@SuppressWarnings("unchecked")
public Map<String,Object> jsonToMap(String json){
    logger.info("params: jsonBody " + json);
    return (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(json), Map.class);
}


public String beanToJSON(Object object,JsonConfig jsonConfig){
    String json = JSONObject.fromObject(object, jsonConfig).toString();
    if (logger.isDebugEnabled()) {
        logger.info("beanToJSON:   " + json);
    }
    return json;
}


public String arrayToJSON(Object object){
    return JSONArray.fromObject(object).toString();
}


public String mapToJSON(Object object){
    return beanToJSON(object);
}


@SuppressWarnings("unchecked")
public T[] jsonToArray(String json,Class<T> clz){
    return (T[]) JSONArray.toArray(JSONArray.fromObject(json), clz);
}


@SuppressWarnings("unchecked")
public T jsonToBean(String json,Class<T> clz){
    return (T) JSONObject.toBean(JSONObject.fromObject(json), clz);
}


public void main(String[] args){
/*
											 * UdpUnitRequest req = new
											 * UdpUnitRequest(); String json =
											 * JsonUtils.beanToJSON(req);
											 * System.out.println(json);
											 * 
											 * 
											 * String restll =
											 * "{\"list\":\"192.168.1.1|11:22:33:44:55:66;192.168.1.2|22:33:44:55:66:77\",\"num\":\"2\"}"
											 * ; String json2 =
											 * "{\"gw_id\":\"yisbus\",\"mac\":\"00:00:00:00:00:00\",\"model\":\"budshi\",
											 * \"res_adver_version\":\"111\",\"res_product_version\":\"111\",\"res_tpl_version\":\"111\",
											 * \"sys_load\":\"111\",\"sys_memfree\":0,\"sys_uptime\":0,\"version\":\"111\",\"wifidog_uptime\":0}"
											 * ;
											 * 
											 * 
											 * String json3 =
											 * "{\"res_tpl_version\":\"0\",\"res_product_version\":\"0\",\"res_adver_version\":\"0\",
											 * \"userversion\":\"0\",\"client_num\":\"3\",
											 * \"client_list\":\"172.16.0.100|11:22:33:44:55:66,172.16.0.101|21:22:23:24:25:26,172.16.0.102|44:33:55:11:22:33\"}"
											 * ;
											 * 
											 * 
											 * HeartResponse resp = new
											 * HeartResponse();
											 * System.out.println
											 * (JsonUtils.beanToJSON(resp));
											 * System
											 * .out.println(JsonUtils.beanToJSON
											 * (new HeartRequest()));
											 * System.out.
											 * println(JsonUtils.jsonToBean
											 * (json2, HeartRequest.class));
											 * 
											 * System.out.println(JsonUtils.
											 * jsonToMap(restll));
											 */
}


public String listToJSON(Object object){
    return arrayToJSON(object);
}


@SuppressWarnings({ "unchecked", "deprecation" })
public List<T> jsonToList(String json,Class<T> clz){
    return JSONArray.toList(JSONArray.fromObject(json), clz);
}


}