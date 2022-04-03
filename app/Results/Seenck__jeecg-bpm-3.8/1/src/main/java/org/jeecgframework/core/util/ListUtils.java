package org.jeecgframework.core.util;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
public class ListUtils {


public List<E> copyTo(List<?> source,Class<E> destinationClass){
    if (source.size() == 0)
        return new ArrayList();
    List<E> res = new ArrayList<E>(source.size());
    for (Object o : source) {
        E e = destinationClass.newInstance();
        BeanUtils.copyProperties(e, o);
        res.add(e);
    }
    return res;
}


public boolean isNullOrEmpty(List list){
    return list == null || list.isEmpty();
}


}