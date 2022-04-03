package com.xwtec.xwserver.util.json.util;
 import com.xwtec.xwserver.util.json.JSONException;
public interface JsonEventListener {


public void onObjectStart()
;

public void onError(JSONException jsone)
;

public void onWarning(String warning)
;

public void onArrayStart()
;

public void onPropertySet(String key,Object value,boolean accumulated)
;

public void onArrayEnd()
;

public void onElementAdded(int index,Object element)
;

public void onObjectEnd()
;

}