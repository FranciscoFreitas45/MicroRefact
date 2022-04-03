package run.halo.app.utils;
 import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
public class ReflectionUtils {

private ReflectionUtils() {
}
public Object getFieldValue(String fieldName,Object object){
    Assert.notNull(fieldName, "FieldName must not be null");
    Assert.notNull(object, "Object type must not be null");
    Object value = null;
    try {
        String firstLetter = fieldName.substring(0, 1).toUpperCase();
        String getter = "get" + firstLetter + fieldName.substring(1);
        Method method = object.getClass().getMethod(getter);
        value = method.invoke(object);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return value;
}


@Nullable
public ParameterizedType getParameterizedTypeBySuperClass(Class<?> superClassType,Class<?> extensionClass){
    if (extensionClass == null) {
        return null;
    }
    return getParameterizedType(superClassType, extensionClass.getGenericSuperclass());
}


@Nullable
public ParameterizedType getParameterizedType(Class<?> interfaceType,Class<?> implementationClass){
    Assert.notNull(interfaceType, "Interface type must not be null");
    Assert.isTrue(interfaceType.isInterface(), "The give type must be an interface");
    if (implementationClass == null) {
        // If the super class is Object parent then return null
        return null;
    }
    // Get parameterized type
    ParameterizedType currentType = getParameterizedType(interfaceType, implementationClass.getGenericInterfaces());
    if (currentType != null) {
        // return the current type
        return currentType;
    }
    Class<?> superclass = implementationClass.getSuperclass();
    return getParameterizedType(interfaceType, superclass);
}


}