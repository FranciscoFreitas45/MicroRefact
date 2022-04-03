package org.jugbd.mnet.utils;
 import org.jugbd.mnet.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import java.lang.reflect.Field;
import java.util.Date;
public class Utils {

 private  Logger log;


public void copyBeanProperties(Object source,Object target,String[] properties){
    final BeanWrapper src = new BeanWrapperImpl(source);
    final BeanWrapper trg = new BeanWrapperImpl(target);
    for (final String propertyName : properties) {
        trg.setPropertyValue(propertyName, src.getPropertyValue(propertyName));
    }
}


public void updatePersistentProperties(T entity,User user){
    try {
        for (Field field : entity.getClass().getSuperclass().getDeclaredFields()) {
            if (field.getName().equals("lastUpdatedBy")) {
                field.setAccessible(true);
                field.set(entity, user);
            } else if (field.getName().equals("dateLastUpdated")) {
                field.setAccessible(true);
                field.set(entity, new Date());
            }
            if (isNew(entity)) {
                if (field.getName().equals("createdBy")) {
                    field.setAccessible(true);
                    field.set(entity, user);
                }
                if (field.getName().equals("dateCreated")) {
                    field.setAccessible(true);
                    field.set(entity, new Date());
                }
            }
        }
    } catch (IllegalAccessException e) {
        log.error("unable to update persistent properties", e);
    }
}


public boolean isNew(T entity){
    log.debug("isNew");
    for (Field field : entity.getClass().getDeclaredFields()) {
        if (field.getName().equalsIgnoreCase("id")) {
            field.setAccessible(true);
            try {
                if (field.get(entity) == null) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                log.error("unable to find whether id is new or not", e);
            }
        }
    }
    return false;
}


}