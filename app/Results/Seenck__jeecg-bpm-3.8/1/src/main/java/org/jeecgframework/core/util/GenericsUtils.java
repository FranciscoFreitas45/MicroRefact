package org.jeecgframework.core.util;
 import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
public class GenericsUtils {

 private  char[] chars;


@SuppressWarnings("unchecked")
public Class getMethodGenericReturnType(Method method){
    return getMethodGenericReturnType(method, 0);
}


@SuppressWarnings("unchecked")
public List<Class> getMethodGenericParameterTypes(Method method){
    return getMethodGenericParameterTypes(method, 0);
}


public String[] getColumnNames(String objClass){
    String[] wageStrArray = null;
    if (objClass != null) {
        Class class1 = Class.forName(objClass);
        // 这里便是获得实体Bean中所有属性的方法
        Field[] field = class1.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < field.length; i++) {
            // 这里不多说了
            sb.append(field[i].getName());
            // 这是分割符 是为了去掉最后那个逗号
            // 比如 如果不去最后那个逗号 最后打印出来的结果是 "id,name,"
            // 去了以后打印出来的是 "id,name"
            if (i < field.length - 1) {
                sb.append(",");
            }
        }
        // split(",");这是根据逗号来切割字符串使字符串变成一个数组
        wageStrArray = sb.toString().split(",");
        return wageStrArray;
    } else {
        return wageStrArray;
    }
}


public Object[] field2Value(Field[] f,Object o){
    Object[] value = new Object[f.length];
    for (int i = 0; i < f.length; i++) {
        value[i] = f[i].get(o);
    }
    return value;
}


public Class getEntityClass(String objClass){
    Class entityClass = null;
    try {
        entityClass = Class.forName(objClass);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    return entityClass;
}


@SuppressWarnings("unchecked")
public Class getFieldGenericType(Field field){
    return getFieldGenericType(field, 0);
}


public String getPasswords(int passLength){
    // 新建一个长度为指定需要密码个数的字符串数组
    String passwords = "";
    Random random = new Random();
    // 保存生成密码的变量
    StringBuilder password = new StringBuilder("");
    for (int m = 1; m <= passLength; m++) {
        // 内循环 从1开始到密码长度 正式开始生成密码
        // 为密码变量随机增加上面字符中的一个
        password.append(chars[random.nextInt(62)]);
    }
    // 将生成出来的密码赋值给密码数组
    passwords = password.toString();
    return passwords;
}


@SuppressWarnings("unchecked")
public Class getSuperClassGenricType(Class clazz){
    return getSuperClassGenricType(clazz, 0);
}


public HttpSession getSession(){
    HttpSession session = null;
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    HttpSession contextSess = attr == null ? session : attr.getRequest().getSession(true);
    return contextSess;
}


}