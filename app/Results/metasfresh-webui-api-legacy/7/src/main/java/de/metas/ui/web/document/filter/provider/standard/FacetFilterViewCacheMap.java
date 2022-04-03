package de.metas.ui.web.document.filter.provider.standard;
 import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class FacetFilterViewCacheMap {

 private  ConcurrentHashMap<String,FacetFilterViewCache> cachesById;


public FacetFilterViewCache computeIfAbsent(String id,Supplier<FacetFilterViewCache> supplier){
    return cachesById.computeIfAbsent(id, k -> supplier.get());
}


public FacetFilterViewCacheMap newInstance(){
    return new FacetFilterViewCacheMap();
}


}