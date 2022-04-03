package com.easyshopping.service.impl;
 import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.dao.BaseDao;
import com.easyshopping.entity.BaseEntity;
import com.easyshopping.service.BaseService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
@Transactional
public class BaseServiceImpl implements BaseService<T, ID>{

 private  String[] UPDATE_IGNORE_PROPERTIES;

 private  BaseDao<T,ID> baseDao;


public void setBaseDao(BaseDao<T,ID> baseDao){
    this.baseDao = baseDao;
}


@Transactional(readOnly = true)
public T find(ID id){
    return baseDao.find(id);
}


@Transactional(readOnly = true)
public List<T> findList(Integer first,Integer count,List<Filter> filters,List<Order> orders){
    return baseDao.findList(first, count, filters, orders);
}


@Transactional(readOnly = true)
public long count(Filter filters){
    return baseDao.count(filters);
}


@Transactional
public void save(T entity){
    baseDao.persist(entity);
}


@Transactional(readOnly = true)
public boolean exists(Filter filters){
    return baseDao.count(filters) > 0;
}


@Transactional
public T update(T entity,String ignoreProperties){
    Assert.notNull(entity);
    if (baseDao.isManaged(entity)) {
        throw new IllegalArgumentException("Entity must not be managed");
    }
    T persistant = baseDao.find(baseDao.getIdentifier(entity));
    if (persistant != null) {
        copyProperties(entity, persistant, (String[]) ArrayUtils.addAll(ignoreProperties, UPDATE_IGNORE_PROPERTIES));
        return update(persistant);
    } else {
        return update(entity);
    }
}


@Transactional(readOnly = true)
public Page<T> findPage(Pageable pageable){
    return baseDao.findPage(pageable);
}


@Transactional(readOnly = true)
public List<T> findAll(){
    return findList(null, null, null, null);
}


@Transactional
public void delete(T entity){
    baseDao.remove(entity);
}


@SuppressWarnings({ "unchecked", "rawtypes" })
public void copyProperties(Object source,Object target,String[] ignoreProperties){
    Assert.notNull(source, "Source must not be null");
    Assert.notNull(target, "Target must not be null");
    PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(target.getClass());
    List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;
    for (PropertyDescriptor targetPd : targetPds) {
        if (targetPd.getWriteMethod() != null && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
            PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
            if (sourcePd != null && sourcePd.getReadMethod() != null) {
                try {
                    Method readMethod = sourcePd.getReadMethod();
                    if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                        readMethod.setAccessible(true);
                    }
                    Object sourceValue = readMethod.invoke(source);
                    Object targetValue = readMethod.invoke(target);
                    if (sourceValue != null && targetValue != null && targetValue instanceof Collection) {
                        Collection collection = (Collection) targetValue;
                        collection.clear();
                        collection.addAll((Collection) sourceValue);
                    } else {
                        Method writeMethod = targetPd.getWriteMethod();
                        if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                            writeMethod.setAccessible(true);
                        }
                        writeMethod.invoke(target, sourceValue);
                    }
                } catch (Throwable ex) {
                    throw new FatalBeanException("Could not copy properties from source to target", ex);
                }
            }
        }
    }
}


}