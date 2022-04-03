package com.example.steam.localstore;
 import java.util.HashMap;
import java.util.Map;
public class LocalStoreKey {

 private  String keyName;

 private  long startTime;

 private  long expiredTime;

 private  Map<String,ExpiredTime> expiredTimeHashMap;

 private  LocalStoreKey FETURED_CAROUSEL_KEY;

 private  LocalStoreKey SPECIAL_CAROUSEL_KEY;

 private  LocalStoreKey NEW_RELEASE_INDEX_KEY;

 private  LocalStoreKey HOT_SELL_INDEX_KEY;

 private  LocalStoreKey UP_COMING_INDEX_KEY;

 private  LocalStoreKey CLASS_CAROUSEL_KEY;

 private  LocalStoreKey NEW_RELEASE_CLASS_KEY;

 private  LocalStoreKey HOT_SELL_CLASS_KEY;

 private  LocalStoreKey UP_COMING_CLASS_KEY;

 private long startTime;

 private long expiredTime;

private LocalStoreKey(String keyName, long startTime, long expiredTime) {
    this.keyName = keyName;
    this.startTime = startTime;
    this.expiredTime = expiredTime;
}
public LocalStoreKey CLASS_CAROUSEL_KEY(){
    if (CLASS_CAROUSEL_KEY == null) {
        synchronized (LocalStoreKey.class) {
            if (CLASS_CAROUSEL_KEY == null) {
                CLASS_CAROUSEL_KEY = new LocalStoreKey("classCarousel", System.currentTimeMillis(), 1000 * 30);
            }
            return CLASS_CAROUSEL_KEY;
        }
    }
    return CLASS_CAROUSEL_KEY;
}


public LocalStoreKey FETURED_CAROUSEL_KEY(){
    if (FETURED_CAROUSEL_KEY == null) {
        synchronized (LocalStoreKey.class) {
            if (FETURED_CAROUSEL_KEY == null) {
                FETURED_CAROUSEL_KEY = new LocalStoreKey("feturedCarousel", System.currentTimeMillis(), 1000 * 30);
            }
            return FETURED_CAROUSEL_KEY;
        }
    }
    return FETURED_CAROUSEL_KEY;
}


public LocalStoreKey NEW_RELEASE_CLASS_KEY(){
    if (NEW_RELEASE_CLASS_KEY == null) {
        synchronized (LocalStoreKey.class) {
            if (NEW_RELEASE_CLASS_KEY == null) {
                NEW_RELEASE_CLASS_KEY = new LocalStoreKey("classNewRelease", System.currentTimeMillis(), 1000 * 50);
            }
            return NEW_RELEASE_CLASS_KEY;
        }
    }
    return NEW_RELEASE_CLASS_KEY;
}


public void setExpiredTime(String page){
    ExpiredTime expiredTime = null;
    if (keyName.equals("classUpComing") || keyName.equals("classHotSell") || keyName.equals("classNewRelease") || keyName.equals("hotSell") || keyName.equals("upComing") || keyName.equals("newRelease")) {
        expiredTime = new ExpiredTime(System.currentTimeMillis(), 10 * 1000);
    } else if (keyName.equals("feturedCarousel") || keyName.equals("classCarousel")) {
        expiredTime = new ExpiredTime(System.currentTimeMillis(), 10 * 1000);
    } else if (keyName.equals("specialCarousel")) {
        expiredTime = new ExpiredTime(System.currentTimeMillis(), 10 * 1000);
    }
    expiredTimeHashMap.put(page, expiredTime);
}


public LocalStoreKey HOT_SELL_CLASS_KEY(){
    if (HOT_SELL_CLASS_KEY == null) {
        synchronized (LocalStoreKey.class) {
            if (HOT_SELL_CLASS_KEY == null) {
                HOT_SELL_CLASS_KEY = new LocalStoreKey("classHotSell", System.currentTimeMillis(), 1000 * 50);
            }
            return HOT_SELL_CLASS_KEY;
        }
    }
    return HOT_SELL_CLASS_KEY;
}


public long getExpiredTime(){
    return expiredTime;
}


public LocalStoreKey NEW_RELEASE_INDEX_KEY(){
    if (NEW_RELEASE_INDEX_KEY == null) {
        synchronized (LocalStoreKey.class) {
            if (NEW_RELEASE_INDEX_KEY == null) {
                NEW_RELEASE_INDEX_KEY = new LocalStoreKey("newRelease", System.currentTimeMillis(), 1000 * 50);
            }
            return NEW_RELEASE_INDEX_KEY;
        }
    }
    return NEW_RELEASE_INDEX_KEY;
}


public LocalStoreKey UP_COMING_CLASS_KEY(){
    if (UP_COMING_CLASS_KEY == null) {
        synchronized (LocalStoreKey.class) {
            if (UP_COMING_CLASS_KEY == null) {
                UP_COMING_CLASS_KEY = new LocalStoreKey("classUpComing", System.currentTimeMillis(), 1000 * 50);
            }
            return UP_COMING_CLASS_KEY;
        }
    }
    return UP_COMING_CLASS_KEY;
}


public LocalStoreKey HOT_SELL_INDEX_KEY(){
    if (HOT_SELL_INDEX_KEY == null) {
        synchronized (LocalStoreKey.class) {
            if (HOT_SELL_INDEX_KEY == null) {
                HOT_SELL_INDEX_KEY = new LocalStoreKey("hotSell", System.currentTimeMillis(), 1000 * 50);
            }
            return HOT_SELL_INDEX_KEY;
        }
    }
    return HOT_SELL_INDEX_KEY;
}


@Override
public int hashCode(){
    return keyName.hashCode();
}


public Map<String,ExpiredTime> getExpiredTimeHashMap(){
    return expiredTimeHashMap;
}


@Override
public boolean equals(Object o){
    if (o == null) {
        return false;
    }
    if (this.getClass() != o.getClass()) {
        return false;
    }
    LocalStoreKey storeKey = (LocalStoreKey) o;
    if (keyName.equals(storeKey.keyName)) {
        return true;
    }
    return false;
}


public LocalStoreKey UP_COMING_INDEX_KEY(){
    if (UP_COMING_INDEX_KEY == null) {
        synchronized (LocalStoreKey.class) {
            if (UP_COMING_INDEX_KEY == null) {
                UP_COMING_INDEX_KEY = new LocalStoreKey("upComing", System.currentTimeMillis(), 1000 * 50);
            }
            return UP_COMING_INDEX_KEY;
        }
    }
    return UP_COMING_INDEX_KEY;
}


public long getStartTime(){
    return startTime;
}


public void setStartTime(){
    this.startTime = System.currentTimeMillis();
}


public String getKeyName(){
    return keyName;
}


public LocalStoreKey SPECIAL_CAROUSEL_KEY(){
    if (SPECIAL_CAROUSEL_KEY == null) {
        synchronized (LocalStoreKey.class) {
            if (SPECIAL_CAROUSEL_KEY == null) {
                SPECIAL_CAROUSEL_KEY = new LocalStoreKey("specialCarousel", System.currentTimeMillis(), 1000 * 40);
            }
            return SPECIAL_CAROUSEL_KEY;
        }
    }
    return SPECIAL_CAROUSEL_KEY;
}


}