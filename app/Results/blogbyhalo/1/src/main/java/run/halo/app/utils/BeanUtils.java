package run.halo.app.utils;
 import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import run.halo.app.exception.BeanUtilsException;
public class BeanUtils {

private BeanUtils() {
}
@Nullable
public T transformFrom(Object source,Class<T> targetClass){
    Assert.notNull(targetClass, "Target class must not be null");
    if (source == null) {
        return null;
    }
    // Init the instance
    try {
        // New instance for the target class
        T targetInstance = targetClass.newInstance();
        // Copy properties
        org.springframework.beans.BeanUtils.copyProperties(source, targetInstance, getNullPropertyNames(source));
        // Return the target instance
        return targetInstance;
    } catch (Exception e) {
        throw new BeanUtilsException("Failed to new " + targetClass.getName() + " instance or copy properties", e);
    }
}


@NonNull
public String[] getNullPropertyNames(Object source){
    return getNullPropertyNameSet(source).toArray(new String[0]);
}


public void updateProperties(Object source,Object target){
    Assert.notNull(source, "source object must not be null");
    Assert.notNull(target, "target object must not be null");
    // Set non null properties from source properties to target properties
    try {
        org.springframework.beans.BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    } catch (BeansException e) {
        throw new BeanUtilsException("Failed to copy properties", e);
    }
}


@NonNull
public List<T> transformFromInBatch(Collection<?> sources,Class<T> targetClass){
    if (CollectionUtils.isEmpty(sources)) {
        return Collections.emptyList();
    }
    // Transform in batch
    return sources.stream().map(source -> transformFrom(source, targetClass)).collect(Collectors.toList());
}


@NonNull
public Set<String> getNullPropertyNameSet(Object source){
    Assert.notNull(source, "source object must not be null");
    BeanWrapperImpl beanWrapper = new BeanWrapperImpl(source);
    PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
    Set<String> emptyNames = new HashSet<>();
    for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
        String propertyName = propertyDescriptor.getName();
        Object propertyValue = beanWrapper.getPropertyValue(propertyName);
        // if property value is equal to null, add it to empty name set
        if (propertyValue == null) {
            emptyNames.add(propertyName);
        }
    }
    return emptyNames;
}


}