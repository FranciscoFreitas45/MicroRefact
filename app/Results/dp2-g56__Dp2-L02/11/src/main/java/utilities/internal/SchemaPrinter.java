package utilities.internal;
 import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class SchemaPrinter {


public void printType(StringBuffer buffer,Object value){
    String type;
    if (value == null)
        type = Object.class.getName();
    else
        type = value.getClass().getName();
    buffer.append(type);
}


public void printPrimitive(StringBuffer buffer,Object value,boolean summary){
    String left, right;
    if (value == null)
        left = right = "";
    else if (value instanceof String)
        left = right = "\"";
    else if (value instanceof Number)
        left = right = "";
    else if (value instanceof Character)
        left = right = "\'";
    else if (value instanceof Boolean)
        left = right = "";
    else {
        left = "<<";
        right = ">>";
    }
    buffer.append(left);
    buffer.append(value);
    buffer.append(right);
}


public boolean isCollection(Object obj){
    boolean result;
    result = (obj != null && obj instanceof Collection);
    return result;
}


public boolean isRecord(Object obj){
    boolean result;
    result = !SchemaPrinter.isValue(obj);
    return result;
}


public boolean isEnum(Object obj){
    boolean result;
    result = (obj == null || obj instanceof Enum);
    return result;
}


public void printRecord(StringBuffer buffer,Object obj,boolean summary){
    List<Class<?>> superClazzes;
    Class<?> clazz;
    buffer.append(obj.toString());
    if (!summary) {
        clazz = obj.getClass();
        superClazzes = new ArrayList<Class<?>>();
        while (clazz != null) {
            superClazzes.add(clazz);
            clazz = clazz.getSuperclass();
        }
        for (int i = superClazzes.size() - 1; i >= 0; i--) {
            clazz = superClazzes.get(i);
            SchemaPrinter.printFieldsInClazz(buffer, clazz, obj);
        }
    }
}


public void print(Object obj){
    String text;
    StringBuffer buffer;
    buffer = new StringBuffer();
    if (SchemaPrinter.isValue(obj))
        SchemaPrinter.printValue(buffer, obj, true);
    else
        SchemaPrinter.printRecord(buffer, obj, false);
    text = buffer.toString();
    System.out.printf("%s%n", text);
}


public boolean isValue(Object obj){
    boolean result;
    result = (SchemaPrinter.isPrimitive(obj) || SchemaPrinter.isArray(obj) || SchemaPrinter.isEnum(obj));
    return result;
}


public void printArray(StringBuffer buffer,Object[] value,boolean summary){
    String separator;
    separator = "";
    buffer.append("[");
    for (final Object item : value) {
        buffer.append(separator);
        SchemaPrinter.printValue(buffer, item, summary);
        separator = ", ";
    }
    buffer.append("]");
}


public void printCollection(StringBuffer buffer,Collection<?> value,boolean summary){
    String separator;
    separator = "";
    buffer.append("[");
    for (final Object item : value) {
        buffer.append(separator);
        SchemaPrinter.printValue(buffer, item, summary);
        separator = ", ";
    }
    buffer.append("]");
}


public void printFieldsInClazz(StringBuffer buffer,Class<?> clazz,Object obj){
    Field[] fields;
    String name;
    Class<?> type;
    Object value;
    fields = clazz.getDeclaredFields();
    AccessibleObject.setAccessible(fields, true);
    for (final Field field : fields) {
        name = field.getName();
        type = field.getType();
        try {
            value = field.get(obj);
        } catch (final Throwable oops) {
            value = String.format("{%s}", oops.getMessage());
        }
        buffer.append("\n\t");
        buffer.append(clazz.getName());
        buffer.append("::");
        buffer.append(name);
        buffer.append(": ");
        SchemaPrinter.printType(buffer, type);
        buffer.append(" = ");
        SchemaPrinter.printValue(buffer, value, true);
    }
}


public boolean isArray(Object obj){
    boolean result;
    result = (obj != null && obj.getClass().getName().charAt(0) == '[');
    return result;
}


public void printValue(StringBuffer buffer,Object value,boolean summary){
    if (SchemaPrinter.isPrimitive(value) || SchemaPrinter.isEnum(value))
        SchemaPrinter.printPrimitive(buffer, value, summary);
    else if (SchemaPrinter.isArray(value))
        SchemaPrinter.printArray(buffer, (Object[]) value, summary);
    else if (SchemaPrinter.isCollection(value))
        SchemaPrinter.printCollection(buffer, (Collection<?>) value, summary);
    else
        SchemaPrinter.printRecord(buffer, value, true);
}


public boolean isPrimitive(Object obj){
    boolean result;
    result = (obj == null || obj instanceof String || obj instanceof Number || obj instanceof Character || obj instanceof Boolean || obj instanceof java.util.Date || obj instanceof java.sql.Date || obj instanceof Timestamp);
    return result;
}


}