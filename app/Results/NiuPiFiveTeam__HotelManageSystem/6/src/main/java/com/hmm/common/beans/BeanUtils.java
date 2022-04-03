package com.hmm.common.beans;
 import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
public class BeanUtils {


public void copyProperties(Object source,Object target) throws BeansException{
    Assert.notNull(source, "Source must not be null");
    Assert.notNull(target, "Target must not be null");
    Class<?> actualEditable = target.getClass();
    PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
    for (PropertyDescriptor targetPd : targetPds) {
        if (targetPd.getWriteMethod() != null) {
            PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
            if (sourcePd != null && sourcePd.getReadMethod() != null) {
                try {
                    Method readMethod = sourcePd.getReadMethod();
                    if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                        readMethod.setAccessible(true);
                    }
                    Object value = readMethod.invoke(source);
                    // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
                    if (value != null) {
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