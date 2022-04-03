package com.xwtec.xwserver.util.json.util.CycleDetectionStrategy;
 import com.xwtec.xwserver.util.json.JSONArray;
import com.xwtec.xwserver.util.json.JSONException;
import com.xwtec.xwserver.util.json.JSONObject;
public class LenientCycleDetectionStrategy extends CycleDetectionStrategy{


public JSONArray handleRepeatedReferenceAsArray(Object reference){
    return new JSONArray();
}


public JSONObject handleRepeatedReferenceAsObject(Object reference){
    return new JSONObject(true);
}


}