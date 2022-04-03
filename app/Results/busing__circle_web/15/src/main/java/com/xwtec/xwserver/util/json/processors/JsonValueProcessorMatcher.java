package com.xwtec.xwserver.util.json.processors;
 import java.util.Set;
@SuppressWarnings("unchecked")
public class JsonValueProcessorMatcher {

 public  JsonValueProcessorMatcher DEFAULT;


public Object getMatch(Class target,Set set){
    if (target != null && set != null && set.contains(target)) {
        return target;
    }
    return null;
}


}