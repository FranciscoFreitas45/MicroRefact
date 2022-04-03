package com.cache;
 public class CacheListener {

 private  CacheManagerImpl cacheManagerImpl;

public CacheListener(CacheManagerImpl cacheManagerImpl) {
    this.cacheManagerImpl = cacheManagerImpl;
}
public void startListen(){
    new Thread() {

        public void run() {
            while (true) {
                for (String key : cacheManagerImpl.getAllKeys()) {
                    if (cacheManagerImpl.isTimeout(key)) {
                        cacheManagerImpl.clearByKey(key);
                    }
                }
            }
        }
    }.start();
}


public void run(){
    while (true) {
        for (String key : cacheManagerImpl.getAllKeys()) {
            if (cacheManagerImpl.isTimeout(key)) {
                cacheManagerImpl.clearByKey(key);
            }
        }
    }
}


}