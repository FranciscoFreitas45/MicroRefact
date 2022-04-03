package com.xwtec.xwserver.util.json.filters;
 import com.xwtec.xwserver.util.json.util.PropertyFilter;
public class TruePropertyFilter implements PropertyFilter{


public boolean apply(Object source,String name,Object value){
    return true;
}


}