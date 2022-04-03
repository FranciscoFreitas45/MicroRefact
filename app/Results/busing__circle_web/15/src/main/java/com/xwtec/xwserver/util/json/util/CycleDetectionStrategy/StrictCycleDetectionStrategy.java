package com.xwtec.xwserver.util.json.util.CycleDetectionStrategy;
 import com.xwtec.xwserver.util.json.JSONArray;
import com.xwtec.xwserver.util.json.JSONException;
import com.xwtec.xwserver.util.json.JSONObject;
public class StrictCycleDetectionStrategy extends CycleDetectionStrategy{


public JSONArray handleRepeatedReferenceAsArray(Object reference){
    throw new JSONException("There is a cycle in the hierarchy!");
}


public JSONObject handleRepeatedReferenceAsObject(Object reference){
    throw new JSONException("There is a cycle in the hierarchy!");
}


}