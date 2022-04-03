package com.kingen.util;
 import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
public class BeanUtils {


public void copyNotNullProperties(Object source,Object target,Class<?> editable,String[] ignoreProperties){
    Assert.notNull(source, "Source must not be null");
    Assert.notNull(target, "Target must not be null");
    Class<?> actualEditable = target.getClass();
    if (editable != null) {
        if (!editable.isInstance(target)) {
            throw new IllegalArgumentException("Target class [" + target.getClass().getName() + "] not assignable to Editable class [" + editable.getName() + "]");
        }
        actualEditable = editable;
    }
    PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
    List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;
    for (PropertyDescriptor targetPd : targetPds) {
        if (targetPd.getWriteMethod() != null && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
            PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
            if (sourcePd != null && sourcePd.getReadMethod() != null) {
                try {
                    Method readMethod = sourcePd.getReadMethod();
                    if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                        readMethod.setAccessible(true);
                    }
                    Object value = readMethod.invoke(source);
                    if (value != null) {
                        // 这里判断以下value是否为空，当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等，如果是String类型，则不需要验证是否为空
                        // if (value != null || readMethod.getReturnType().getName().equals("java.lang.String")) {// 这里判断以下value是否为空，当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等，如果是String类型，则不需要验证是否为空
                        boolean isEmpty = false;
                        if (value instanceof Set) {
                            Set s = (Set) value;
                            if (s == null || s.isEmpty()) {
                                isEmpty = true;
                            }
                        } else if (value instanceof Map) {
                            Map m = (Map) value;
                            if (m == null || m.isEmpty()) {
                                isEmpty = true;
                            }
                        } else if (value instanceof List) {
                            List l = (List) value;
                            if (l == null || l.size() < 1) {
                                isEmpty = true;
                            }
                        } else if (value instanceof Collection) {
                            Collection c = (Collection) value;
                            if (c == null || c.size() < 1) {
                                isEmpty = true;
                            }
                        } else if (value instanceof String) {
                            // 加入String 空串的判断 wj
                            String c = (String) value;
                            if (StringUtils.isEmpty(c)) {
                                isEmpty = true;
                            }
                        }
                        if (!isEmpty) {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        }
                    }
                } catch (Throwable ex) {
                    throw new FatalBeanException("Could not copy properties from source to target", ex);
                }
            }
        }
    }
}


}