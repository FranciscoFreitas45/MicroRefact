package com.xwtec.xwserver.util.json.processors.PropertyNameProcessorMatcher;
 import java.util.Set;
public class DefaultPropertyNameProcessorMatcher extends PropertyNameProcessorMatcher{


public Object getMatch(Class target,Set set){
    if (target != null && set != null && set.contains(target)) {
        return target;
    }
    return null;
}


}