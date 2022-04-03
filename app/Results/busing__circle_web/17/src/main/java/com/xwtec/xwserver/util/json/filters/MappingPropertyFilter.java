package com.xwtec.xwserver.util.json.filters;
 import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.xwtec.xwserver.util.json.util.PropertyFilter;
@SuppressWarnings("unchecked")
public class MappingPropertyFilter implements PropertyFilter{

 private  Map filters;

public MappingPropertyFilter() {
    this(null);
}public MappingPropertyFilter(Map filters) {
    if (filters != null) {
        for (Iterator i = filters.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry entry = (Map.Entry) i.next();
            Object key = entry.getKey();
            Object filter = entry.getValue();
            if (filter instanceof PropertyFilter) {
                this.filters.put(key, filter);
            }
        }
    }
}
public void removePropertyFilter(Object target){
    if (target != null) {
        filters.remove(target);
    }
}


public boolean keyMatches(Object key,Object source,String name,Object value)


public boolean apply(Object source,String name,Object value){
    for (Iterator i = filters.entrySet().iterator(); i.hasNext(); ) {
        Map.Entry entry = (Map.Entry) i.next();
        Object key = entry.getKey();
        if (keyMatches(key, source, name, value)) {
            PropertyFilter filter = (PropertyFilter) entry.getValue();
            return filter.apply(source, name, value);
        }
    }
    return false;
}


public void addPropertyFilter(Object target,PropertyFilter filter){
    if (filter != null) {
        filters.put(target, filter);
    }
}


}