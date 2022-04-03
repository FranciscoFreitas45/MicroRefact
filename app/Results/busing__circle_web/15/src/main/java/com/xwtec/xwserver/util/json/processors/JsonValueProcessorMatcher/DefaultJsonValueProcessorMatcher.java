package com.xwtec.xwserver.util.json.processors.JsonValueProcessorMatcher;
 import java.util.Set;
public class DefaultJsonValueProcessorMatcher extends JsonValueProcessorMatcher{


public Object getMatch(Class target,Set set){
    if (target != null && set != null && set.contains(target)) {
        return target;
    }
    return null;
}


}