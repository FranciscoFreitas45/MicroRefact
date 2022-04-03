package com.xwtec.xwserver.util.json.AbstractJSON;
 import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.xwtec.xwserver.util.json.util.JSONUtils;
import com.xwtec.xwserver.util.json.util.JsonEventListener;
public class CycleSet extends ThreadLocal{


public Set getSet(){
    Set set = (Set) ((SoftReference) get()).get();
    if (set == null) {
        set = new HashSet();
        set(new SoftReference(set));
    }
    return set;
}


public Object initialValue(){
    return new SoftReference(new HashSet());
}


}