package com.xwtec.xwserver.util.json.util.JavaIdentifierTransformer;
 import org.apache.commons.lang.StringUtils;
import com.xwtec.xwserver.util.json.JSONException;
public class StrictJavaIdentifierTransformer extends JavaIdentifierTransformer{


public String transformToJavaIdentifier(String str){
    throw new JSONException("'" + str + "' is not a valid Java identifier.");
}


}