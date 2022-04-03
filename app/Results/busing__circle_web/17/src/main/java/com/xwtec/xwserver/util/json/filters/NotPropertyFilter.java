package com.xwtec.xwserver.util.json.filters;
 import com.xwtec.xwserver.util.json.util.PropertyFilter;
public class NotPropertyFilter implements PropertyFilter{

 private  PropertyFilter filter;

public NotPropertyFilter(PropertyFilter filter) {
    this.filter = filter;
}
public boolean apply(Object source,String name,Object value){
    if (filter != null) {
        return !filter.apply(source, name, value);
    }
    return false;
}


}