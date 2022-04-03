package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CacheInstanceController {

 private CacheInstance cacheinstance;


@GetMapping
("/getAgentStatusCacheBean")
public CacheBean getAgentStatusCacheBean(){
  return cacheinstance.getAgentStatusCacheBean();
}


@GetMapping
("/getAgentUserCacheBean")
public CacheBean getAgentUserCacheBean(){
  return cacheinstance.getAgentUserCacheBean();
}


@GetMapping
("/getOnlineCacheBean")
public CacheBean getOnlineCacheBean(){
  return cacheinstance.getOnlineCacheBean();
}


@GetMapping
("/getSystemCacheBean")
public CacheBean getSystemCacheBean(){
  return cacheinstance.getSystemCacheBean();
}


@GetMapping
("/getIMRCacheBean")
public CacheBean getIMRCacheBean(){
  return cacheinstance.getIMRCacheBean();
}


@GetMapping
("/getCallCenterCacheBean")
public CacheBean getCallCenterCacheBean(){
  return cacheinstance.getCallCenterCacheBean();
}


@GetMapping
("/getCallCenterAgentCacheBean")
public CacheBean getCallCenterAgentCacheBean(){
  return cacheinstance.getCallCenterAgentCacheBean();
}


@GetMapping
("/getApiUserCacheBean")
public CacheBean getApiUserCacheBean(){
  return cacheinstance.getApiUserCacheBean();
}


@GetMapping
("/getJobCacheBean")
public CacheBean getJobCacheBean(){
  return cacheinstance.getJobCacheBean();
}


@GetMapping
("/getCallOutCacheBean")
public CacheBean getCallOutCacheBean(){
  return cacheinstance.getCallOutCacheBean();
}


@GetMapping
("/getQcQueueBean")
public CacheBean getQcQueueBean(){
  return cacheinstance.getQcQueueBean();
}


}