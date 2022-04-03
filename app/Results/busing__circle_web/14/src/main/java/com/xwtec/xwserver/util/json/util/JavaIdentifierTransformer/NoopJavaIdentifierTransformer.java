package com.xwtec.xwserver.util.json.util.JavaIdentifierTransformer;
 import org.apache.commons.lang.StringUtils;
import com.xwtec.xwserver.util.json.JSONException;
public class NoopJavaIdentifierTransformer extends JavaIdentifierTransformer{


public String transformToJavaIdentifier(String str){
    return str;
}


}