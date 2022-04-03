package com.ukefu.webim.service.cache;
 import com.ukefu.webim.service.cache.hazelcast.HazlcastCacheHelper;
import Interface.CacheInstance;
public class CacheHelper {

 private  CacheHelper instance;

 private  CacheInstance cacheInstance;


public CacheBean getIMRCacheBean(){
    return cacheInstance != null ? cacheInstance.getIMRCacheBean() : null;
}


public CacheBean getCallCenterAgentCacheBean(){
    return cacheInstance != null ? cacheInstance.getCallCenterAgentCacheBean() : null;
}


public CacheBean getSystemCacheBean(){
    return cacheInstance != null ? cacheInstance.getSystemCacheBean() : null;
}


public CacheBean getOnlineUserCacheBean(){
    return cacheInstance != null ? cacheInstance.getOnlineCacheBean() : null;
}


public CacheBean getAgentStatusCacheBean(){
    return cacheInstance != null ? cacheInstance.getAgentStatusCacheBean() : null;
}


public CacheBean getApiUserCacheBean(){
    return cacheInstance != null ? cacheInstance.getApiUserCacheBean() : null;
}


public CacheBean getCallCenterCacheBean(){
    return cacheInstance != null ? cacheInstance.getCallCenterCacheBean() : null;
}


public CacheBean getJobCacheBean(){
    return cacheInstance != null ? cacheInstance.getJobCacheBean() : null;
}


public CacheHelper getInstance(){
    return instance;
}


public CacheBean getQcQueueCacheBean(){
    return cacheInstance != null ? cacheInstance.getQcQueueBean() : null;
}


public CacheBean getCallOutCacheBean(){
    return cacheInstance != null ? cacheInstance.getCallOutCacheBean() : null;
}


public CacheBean getAgentUserCacheBean(){
    return cacheInstance != null ? cacheInstance.getAgentUserCacheBean() : null;
}


}