package DTO;
 import java.util.HashSet;
import java.util.Set;
import org.hibernate.collection.internal.PersistentSet;
import com.alibaba.fastjson.serializer.PropertyFilter;
public class FastjsonFilter implements PropertyFilter{

 private  Set<String> includes;

 private  Set<String> excludes;


public Set<String> getIncludes(){
    return includes;
}


public Set<String> getExcludes(){
    return excludes;
}


}