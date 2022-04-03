package org.gliderwiki.framework.orm.sql.util;
 import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
public class AnnotationUtil {


public List<String> getColumnNames(Class<?> clazz){
    List<String> names = new LinkedList<String>();
    for (Field field : clazz.getDeclaredFields()) {
        if (field.isAnnotationPresent(Column.class)) {
            Column c = field.getAnnotation(Column.class);
            names.add("".equals(c.name()) ? field.getName() : c.name());
        }
    }
    return names;
}


public List<String> getNotNullColumnNames(Object object){
    List<String> names = new LinkedList<String>();
    BeanWrapper bean = new BeanWrapperImpl(object);
    for (Field field : object.getClass().getDeclaredFields()) {
        if (field.isAnnotationPresent(Column.class)) {
            Object value = bean.getPropertyValue(field.getName());
            if (value != null && !"".equals(value)) {
                Column c = field.getAnnotation(Column.class);
                names.add("".equals(c.name()) ? field.getName() : c.name());
            }
        }
    }
    return names;
}


public String getTableName(Class<?> clazz){
    Table t = clazz.getAnnotation(Table.class);
    return t.value();
}


public List<String> getNonAutoIncrementColumnNames(Class<?> clazz){
    List<String> names = new LinkedList<String>();
    for (Field field : clazz.getDeclaredFields()) {
        if (field.isAnnotationPresent(Column.class)) {
            Column c = field.getAnnotation(Column.class);
            if (!c.autoIncrement())
                names.add("".equals(c.name()) ? field.getName() : c.name());
        }
    }
    return names;
}


public List<String> getNonPrimaryKeyColumnNames(Class<?> clazz){
    List<String> names = new LinkedList<String>();
    for (Field field : clazz.getDeclaredFields()) {
        if (field.isAnnotationPresent(Column.class)) {
            Column c = field.getAnnotation(Column.class);
            if (!c.primaryKey())
                names.add("".equals(c.name()) ? field.getName() : c.name());
        }
    }
    return names;
}


public List<String> getPrimaryKeyColumnNames(Class<?> clazz){
    List<String> names = new LinkedList<String>();
    for (Field field : clazz.getDeclaredFields()) {
        if (field.isAnnotationPresent(Column.class)) {
            Column c = field.getAnnotation(Column.class);
            if (c.primaryKey())
                names.add("".equals(c.name()) ? field.getName() : c.name());
        }
    }
    return names;
}


}