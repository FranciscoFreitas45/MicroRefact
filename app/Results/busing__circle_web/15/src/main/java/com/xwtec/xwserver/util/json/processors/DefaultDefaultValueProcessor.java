package com.xwtec.xwserver.util.json.processors;
 import com.xwtec.xwserver.util.json.JSONArray;
import com.xwtec.xwserver.util.json.JSONNull;
import com.xwtec.xwserver.util.json.util.JSONUtils;
@SuppressWarnings("unchecked")
public class DefaultDefaultValueProcessor implements DefaultValueProcessor{


public Object getDefaultValue(Class type){
    if (JSONUtils.isArray(type)) {
        return new JSONArray();
    } else if (JSONUtils.isNumber(type)) {
        if (JSONUtils.isDouble(type)) {
            return new Double(0);
        } else {
            return new Integer(0);
        }
    } else if (JSONUtils.isBoolean(type)) {
        return Boolean.FALSE;
    } else if (JSONUtils.isString(type)) {
        return "";
    }
    return JSONNull.getInstance();
}


}