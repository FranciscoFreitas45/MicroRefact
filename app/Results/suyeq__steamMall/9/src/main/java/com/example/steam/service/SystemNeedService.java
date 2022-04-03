package com.example.steam.service;
 import com.example.steam.dao.SystemNeedDao;
import com.example.steam.entity.SystemNeed;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.SystemNeedKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SystemNeedService {

@Autowired
 private SystemNeedDao systemNeedDao;

@Autowired
 private RedisService redisService;


public long addSystemNeed(SystemNeed systemNeed){
    systemNeedDao.addSystemNeed(systemNeed);
    return systemNeed.getId();
}


public SystemNeed findSystemNeedById(long id){
    SystemNeed systemNeed = redisService.get(SystemNeedKey.SYSTEMNEED_ID, id + "", SystemNeed.class);
    if (systemNeed != null) {
        return systemNeed;
    }
    systemNeed = systemNeedDao.findSystemNeedById(id);
    redisService.set(SystemNeedKey.SYSTEMNEED_ID, id + "", systemNeed);
    return systemNeed;
}


}