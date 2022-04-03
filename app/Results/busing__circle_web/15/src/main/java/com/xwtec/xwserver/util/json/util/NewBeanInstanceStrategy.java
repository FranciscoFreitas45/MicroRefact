package com.xwtec.xwserver.util.json.util;
 import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import com.xwtec.xwserver.util.json.JSONObject;
@SuppressWarnings("unchecked")
public class NewBeanInstanceStrategy {

 public  NewBeanInstanceStrategy DEFAULT;

 private  Object[] EMPTY_ARGS;

 private  Class[] EMPTY_PARAM_TYPES;


public Object newInstance(Class target,JSONObject source){
    if (target != null) {
        Constructor c = target.getDeclaredConstructor(EMPTY_PARAM_TYPES);
        c.setAccessible(true);
        try {
            return c.newInstance(EMPTY_ARGS);
        } catch (InstantiationException e) {
            // getCause() was added on jdk 1.4
            String cause = "";
            try {
                cause = e.getCause() != null ? "\n" + e.getCause().getMessage() : "";
            } catch (Throwable t) {
            /* ignore */
            }
            throw new InstantiationException("Instantiation of \"" + target + "\" failed. " + "It's probably because class is an interface, " + "abstract class, array class, primitive type or void." + cause);
        }
    }
    return null;
}


}