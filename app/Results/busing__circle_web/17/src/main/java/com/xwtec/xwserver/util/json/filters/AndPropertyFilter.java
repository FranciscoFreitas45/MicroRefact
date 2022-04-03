package com.xwtec.xwserver.util.json.filters;
 import com.xwtec.xwserver.util.json.util.PropertyFilter;
public class AndPropertyFilter implements PropertyFilter{

 private  PropertyFilter filter1;

 private  PropertyFilter filter2;

public AndPropertyFilter(PropertyFilter filter1, PropertyFilter filter2) {
    this.filter1 = filter1;
    this.filter2 = filter2;
}
public boolean apply(Object source,String name,Object value){
    if (filter1 != null && filter1.apply(source, name, value) && filter2 != null && filter2.apply(source, name, value)) {
        return true;
    }
    return false;
}


}