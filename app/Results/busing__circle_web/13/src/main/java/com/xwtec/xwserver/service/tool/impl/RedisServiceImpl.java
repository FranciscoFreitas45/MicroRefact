package com.xwtec.xwserver.service.tool.impl;
 import java.util.Set;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.service.tool.IRedisService;
import com.DTO.Page;
@Service
public class RedisServiceImpl implements IRedisService{

 private  Logger logger;

@Resource
 private JedisPool jedisPool;


public Set<String> getKeys(Page page){
    logger.info("RedisServiceImpl.getKeys param: " + page.getSearchParam());
    // jedis对象
    Jedis jedis = null;
    // keys列表
    Set<String> keys = null;
    try {
        // 获取连接
        jedis = jedisPool.getResource();
        // 根据前台传过来的规则获取缓存key列表
        if (null != page.getSearchParam() && null != page.getSearchParam().get("keys")) {
            keys = jedis.keys(page.getSearchParam().get("keys"));
        }
    } finally {
        // 使用完毕后将jedis对象归还连接池
        if (jedis != null)
            jedisPool.returnResource(jedis);
    }
    return keys;
}


public long Ttl(String key){
    // 返回时间
    long result = 0L;
    // jedis对象
    Jedis jedis = null;
    try {
        // 获取连接
        jedis = jedisPool.getResource();
        result = jedis.ttl(key);
    } finally {
        // 使用完毕后将jedis对象归还连接池
        if (jedis != null)
            jedisPool.returnResource(jedis);
    }
    return result;
}


}