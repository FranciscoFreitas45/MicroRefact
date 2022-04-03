package com.ipe.common.util;
 import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import java.lang.reflect.Method;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
public class CollectionSort {

 private  Comparator comparator;

 private  String[] sortBy;

 private  int order;

public CollectionSort(String[] sortBy, int order) {
    this.order = order;
    this.sortBy = sortBy;
}
public int compare(Object o1,Object o2){
    for (int i = 0; i < sortBy.length; i++) {
        Object value1 = getFieldValue(sortBy[i], o1);
        Object value2 = getFieldValue(sortBy[i], o2);
        int result = comparator.compare(value1, value2);
    }
    return -1;
}


public Object getFieldValue(String filedName,Object o){
    try {
        String getter = "get" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
        Method method = o.getClass().getMethod(getter, new Class[] {});
        return method.invoke(o, new Object[] {});
    } catch (Exception e) {
        // To change body of catch statement use File | Settings | File Templates.
        e.printStackTrace();
    }
    return null;
}


public void sortList(List list,String prop,boolean isAsc){
    Comparator beanProp = new BeanComparator(prop);
    Comparator comparator1 = new ComparatorChain(beanProp, !isAsc);
    Collections.sort(list, comparator1);
}


}