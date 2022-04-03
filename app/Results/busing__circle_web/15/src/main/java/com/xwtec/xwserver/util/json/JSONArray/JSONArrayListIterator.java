package com.xwtec.xwserver.util.json.JSONArray;
 import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import net.sf.ezmorph.Morpher;
import net.sf.ezmorph.object.IdentityObjectMorpher;
import org.apache.commons.lang.StringUtils;
import com.xwtec.xwserver.util.json.processors.JsonValueProcessor;
import com.xwtec.xwserver.util.json.processors.JsonVerifier;
import com.xwtec.xwserver.util.json.util.JSONTokener;
import com.xwtec.xwserver.util.json.util.JSONUtils;
public class JSONArrayListIterator implements ListIterator{

 private int currentIndex;

 private int lastIndex;

JSONArrayListIterator() {
}JSONArrayListIterator(int index) {
    currentIndex = index;
}
public Object next(){
    try {
        Object next = get(currentIndex);
        lastIndex = currentIndex++;
        return next;
    } catch (IndexOutOfBoundsException e) {
        throw new NoSuchElementException();
    }
}


public void add(Object obj){
    try {
        JSONArray.this.add(currentIndex++, obj);
        lastIndex = -1;
    } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
    }
}


public void set(Object obj){
    if (lastIndex == -1) {
        throw new IllegalStateException();
    }
    try {
        JSONArray.this.set(lastIndex, obj);
    } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
    }
}


public Object previous(){
    try {
        int index = currentIndex - 1;
        Object previous = get(index);
        lastIndex = currentIndex = index;
        return previous;
    } catch (IndexOutOfBoundsException e) {
        throw new NoSuchElementException();
    }
}


public boolean hasPrevious(){
    return currentIndex != 0;
}


public boolean hasNext(){
    return currentIndex != size();
}


public int nextIndex(){
    return currentIndex;
}


public void remove(){
    if (lastIndex == -1)
        throw new IllegalStateException();
    try {
        JSONArray.this.remove(lastIndex);
        if (lastIndex < currentIndex) {
            currentIndex--;
        }
        lastIndex = -1;
    } catch (IndexOutOfBoundsException e) {
        throw new ConcurrentModificationException();
    }
}


public int previousIndex(){
    return currentIndex - 1;
}


}