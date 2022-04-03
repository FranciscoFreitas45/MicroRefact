package com.xwtec.xwserver.util.json.filters;
 import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.xwtec.xwserver.util.json.util.PropertyFilter;
@SuppressWarnings("unchecked")
public class CompositePropertyFilter implements PropertyFilter{

 private  List filters;

public CompositePropertyFilter() {
    this(null);
}public CompositePropertyFilter(List filters) {
    if (filters != null) {
        for (Iterator i = filters.iterator(); i.hasNext(); ) {
            Object filter = i.next();
            if (filter instanceof PropertyFilter) {
                this.filters.add(filter);
            }
        }
    }
}
public void removePropertyFilter(PropertyFilter filter){
    if (filter != null) {
        filters.remove(filter);
    }
}


public boolean apply(Object source,String name,Object value){
    for (Iterator i = filters.iterator(); i.hasNext(); ) {
        PropertyFilter filter = (PropertyFilter) i.next();
        if (filter.apply(source, name, value)) {
            return true;
        }
    }
    return false;
}


public void addPropertyFilter(PropertyFilter filter){
    if (filter != null) {
        filters.add(filter);
    }
}


}