package com.xwtec.xwserver.util.json.util.PropertyExclusionClassMatcher;
 import java.util.Set;
public class DefaultPropertyExclusionClassMatcher extends PropertyExclusionClassMatcher{


public Object getMatch(Class target,Set set){
    if (target != null && set != null && set.contains(target)) {
        return target;
    }
    return null;
}


}