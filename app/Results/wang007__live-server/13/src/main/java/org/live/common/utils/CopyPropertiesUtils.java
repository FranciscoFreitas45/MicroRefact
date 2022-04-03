package org.live.common.utils;
 import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;
public class CopyPropertiesUtils {


public String[] getNullPropertyNames(Object source){
    final BeanWrapper wrapper = new BeanWrapperImpl(source);
    Set<String> nullPropertyNameSet = new HashSet<String>();
    PropertyDescriptor[] pds = wrapper.getPropertyDescriptors();
    for (PropertyDescriptor pd : pds) {
        if (wrapper.getPropertyValue(pd.getName()) == null) {
            nullPropertyNameSet.add(pd.getName());
        }
    }
    String[] result = new String[nullPropertyNameSet.size()];
    return nullPropertyNameSet.toArray(result);
}


public void copyPropertiesIgnoreNull(Object dest,Object source){
    BeanUtils.copyProperties(source, dest, getNullPropertyNames(source));
}


}