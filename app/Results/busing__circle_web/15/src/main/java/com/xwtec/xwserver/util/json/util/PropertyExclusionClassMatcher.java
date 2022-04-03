package com.xwtec.xwserver.util.json.util;
 import java.util.Set;
@SuppressWarnings("unchecked")
public class PropertyExclusionClassMatcher {

 public  PropertyExclusionClassMatcher DEFAULT;


public Object getMatch(Class target,Set set){
    if (target != null && set != null && set.contains(target)) {
        return target;
    }
    return null;
}


}