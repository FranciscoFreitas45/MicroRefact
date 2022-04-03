package com.xwtec.xwserver.util.json.processors.JsonBeanProcessorMatcher;
 import java.util.Set;
public class DefaultJsonBeanProcessorMatcher extends JsonBeanProcessorMatcher{


public Object getMatch(Class target,Set set){
    if (target != null && set != null && set.contains(target)) {
        return target;
    }
    return null;
}


}