package com.xwtec.xwserver.util.json.util.CycleDetectionStrategy;
 import com.xwtec.xwserver.util.json.JSONArray;
import com.xwtec.xwserver.util.json.JSONException;
import com.xwtec.xwserver.util.json.JSONObject;
public class LenientNoRefCycleDetectionStrategy extends CycleDetectionStrategy{


public JSONArray handleRepeatedReferenceAsArray(Object reference){
    return IGNORE_PROPERTY_ARR;
}


public JSONObject handleRepeatedReferenceAsObject(Object reference){
    return IGNORE_PROPERTY_OBJ;
}


}