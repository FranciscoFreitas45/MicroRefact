package com.xwtec.xwserver.util.json.util;
 import com.xwtec.xwserver.util.json.JSONArray;
import com.xwtec.xwserver.util.json.JSONException;
import com.xwtec.xwserver.util.json.JSONObject;
public class CycleDetectionStrategy {

 public  JSONArray IGNORE_PROPERTY_ARR;

 public  JSONObject IGNORE_PROPERTY_OBJ;

 public  CycleDetectionStrategy LENIENT;

 public  CycleDetectionStrategy NOPROP;

 public  CycleDetectionStrategy STRICT;


public JSONArray handleRepeatedReferenceAsArray(Object reference){
    throw new JSONException("There is a cycle in the hierarchy!");
}


public JSONObject handleRepeatedReferenceAsObject(Object reference){
    throw new JSONException("There is a cycle in the hierarchy!");
}


}