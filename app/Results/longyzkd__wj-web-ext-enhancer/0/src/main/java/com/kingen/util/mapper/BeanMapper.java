package com.kingen.util.mapper;
 import java.util.Collection;
import java.util.List;
import org.dozer.DozerBeanMapper;
import com.google.common.collect.Lists;
import com.kingen.util.SpringContextHolder;
public class BeanMapper {

 private  DozerBeanMapper dozer;


@SuppressWarnings("rawtypes")
public List<T> mapList(Collection sourceList,Class<T> destinationClass){
    List<T> destinationList = Lists.newArrayList();
    for (Object sourceObject : sourceList) {
        T destinationObject = dozer.map(sourceObject, destinationClass);
        destinationList.add(destinationObject);
    }
    return destinationList;
}


public void copy(Object source,Object destinationObject){
    dozer.map(source, destinationObject);
}


public T map(Object source,Class<T> destinationClass){
    return dozer.map(source, destinationClass);
}


}