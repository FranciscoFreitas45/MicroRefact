package com.qidian.hcm.common.utils;
 import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
public class CommonUtils {

private CommonUtils() {
}
public String encoderByMd5(String str){
    return DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
}


public String[] getNullPropertyNames(Object source){
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
    Set<String> emptyNames = new HashSet<>();
    for (java.beans.PropertyDescriptor pd : pds) {
        Object srcValue = src.getPropertyValue(pd.getName());
        if (srcValue == null) {
            emptyNames.add(pd.getName());
        }
    }
    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
}


}