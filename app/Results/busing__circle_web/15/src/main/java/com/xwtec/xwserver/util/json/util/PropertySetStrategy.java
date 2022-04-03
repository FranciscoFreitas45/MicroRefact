package com.xwtec.xwserver.util.json.util;
 import java.lang.reflect.Field;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;
import com.xwtec.xwserver.util.json.JSONException;
import com.xwtec.xwserver.util.json.JsonConfig;
@SuppressWarnings("unchecked")
public class PropertySetStrategy {

 public  PropertySetStrategy DEFAULT;


public void _setProperty(Object bean,String key,Object value){
    try {
        PropertyUtils.setSimpleProperty(bean, key, value);
    } catch (Exception e) {
        throw new JSONException(e);
    }
}


public void setProperty(Object bean,String key,Object value,JsonConfig jsonConfig){
    if (bean instanceof Map) {
        ((Map) bean).put(key, value);
    } else {
        if (!jsonConfig.isIgnorePublicFields()) {
            try {
                Field field = bean.getClass().getField(key);
                if (field != null)
                    field.set(bean, value);
            } catch (Exception e) {
                _setProperty(bean, key, value);
            }
        } else {
            _setProperty(bean, key, value);
        }
    }
}


}