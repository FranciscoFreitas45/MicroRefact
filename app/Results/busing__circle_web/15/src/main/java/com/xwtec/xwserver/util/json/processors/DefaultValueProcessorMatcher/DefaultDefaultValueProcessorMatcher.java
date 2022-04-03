package com.xwtec.xwserver.util.json.processors.DefaultValueProcessorMatcher;
 import java.util.Set;
public class DefaultDefaultValueProcessorMatcher extends DefaultValueProcessorMatcher{


public Object getMatch(Class target,Set set){
    if (target != null && set != null && set.contains(target)) {
        return target;
    }
    return null;
}


}