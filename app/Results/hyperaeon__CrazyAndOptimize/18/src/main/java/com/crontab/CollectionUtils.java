package com.crontab;
 import java.util.Collection;
public class CollectionUtils {

private CollectionUtils() {
}
public boolean isEmpty(Collection collection){
    return collection == null || collection.isEmpty();
}


public boolean isNotEmpty(Collection collection){
    return !isEmpty(collection);
}


}