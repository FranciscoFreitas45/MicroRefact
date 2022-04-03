package com.xwtec.xwserver.util.jedis;
 import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import com.xwtec.xwserver.constant.ConstantKeys;
import com.xwtec.xwserver.exception.SPTException;
public class JedisUtil {

 private  Logger logger;

@Resource(name = "jedisPool")
 public  JedisPool jedisPool;


public boolean lpush(String key,String strings){
    // 声明一个链接对象
    Jedis jedis = null;
    // 返回标记,默认成功
    boolean flag = true;
    try {
        // 获取资源
        jedis = this.getResource();
        // 资源不为空则执行注入操作 否则返回注入失败
        if (jedis != null)
            jedis.lpush(key, strings);
        else
            flag = false;
    } finally {
        // 归还资源
        if (jedis != null)
            this.returnResource(jedis);
    }
    return flag;
}


public Jedis getResource(){
    logger.debug("[JedisUtil:getResource]: get jedis Resource from Pool...");
    // 声明jedis对象
    Jedis jedis = null;
    // 出现异常已经循环获取的次数
    int cycleTimes = 0;
    try {
        // 从pool中获取jedis对象
        jedis = this.jedisPool.getResource();
    } catch (JedisConnectionException ex) {
        try {
            // 获取占用异常,捕获异常,等待0.5秒后继续执行获取
            logger.debug("[JedisUtil:getResource]:redis pool is full,Program will sleep 0.5s to wait an idle.message:\n" + ex.getMessage());
            while (cycleTimes < ConstantKeys.RedisUtilKey.CYCLE_TIMES) {
                // 获取次数 +1;
                cycleTimes++;
                // 等待0.5s
                Thread.sleep(ConstantKeys.RedisUtilKey.WAIT_TIME);
                logger.debug("[JedisUtil:getResource]:waiting to get an idle...");
                try {
                    // 重新获取jedis对象
                    jedis = this.jedisPool.getResource();
                } catch (JedisConnectionException ex1) {
                    logger.debug("[JedisUtil:getResource]:get an idle failed.Program will try again.");
                    // 出现获取异常,继续执行
                    continue;
                }
                // 获取到对象后跳出循环
                if (jedis != null) {
                    // 输出获取成功的消息
                    logger.debug("[JedisUtil:getResource]:get an idle success.");
                    break;
                } else {
                    // 如果获取出对象为null,则继续循环等待获取.
                    logger.debug("[JedisUtil:getResource]:get an idle is null.Program will try again.");
                    continue;
                }
            }
        }// 处理线程截断异常
         catch (InterruptedException e) {
            logger.error("[JedisUtil:getResource]:get jedis object failed.message:\n" + e.getMessage());
        }
    }
    // 获取对象如果不为空则返回
    if (jedis != null) {
        logger.debug("[JedisUtil:getResource]: get jedis Resource from Pool success.");
    } else {
        // 当循环获取超过三次直接抛出异常 返回null
        logger.error("[JedisUtil:getResource]:get jedis object failed.if redis server is runing,please check the configration and Network connection.");
        throw new SPTException("server can not get jedis Resource!");
    }
    return jedis;
}


public void returnResource(Jedis jedis){
    try {
        if (jedis != null)
            // 归还对象至pool
            this.jedisPool.returnResource(jedis);
        logger.debug("[JedisUtil:returnResource]: return jedis Resource to Pool  ...");
    } catch (JedisConnectionException ex) {
        // 归还失败,强制销毁该链接
        this.jedisPool.returnBrokenResource(jedis);
    }
}


public List<String> getRedisHashValue(String key){
    // 声明一个链接对象
    Jedis jedis = null;
    List<String> hash = null;
    try {
        // 获取jedis资源
        jedis = this.getResource();
        // 资源不为空,则获取对应的value
        if (jedis != null) {
            hash = jedis.hvals(key);
        }
    } finally {
        if (jedis != null) {
            this.returnResource(jedis);
        }
    }
    return hash;
}


public long incr(String key){
    // 声明一个链接对象
    Jedis jedis = null;
    // 获取的键值所对应的值
    long value = 0;
    try {
        // 获取jedis资源
        jedis = this.getResource();
        // 资源不为空,则获取对应的value
        if (jedis != null)
            value = jedis.incr(key);
    } finally {
        if (jedis != null)
            this.returnResource(jedis);
    }
    return value;
}


public boolean setRedisStrValue(String key,String value,int seconds){
    // 返回标记,默认成功
    boolean flag = true;
    // 如果设置时间为负数,则无上限时间
    if (seconds <= 0) {
        this.setRedisStrValue(key, value);
        return flag;
    }
    // 声明一个链接对象
    Jedis jedis = null;
    try {
        // 获取资源
        jedis = this.getResource();
        // 资源不为空则执行注入操作 否则返回注入失败
        if (jedis != null) {
            // 判断是否已经存在,如果已经存在则删除
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            // 该方法内容为,如果含有相同的key值,则不覆盖.
            jedis.setex(key, seconds, value);
        } else
            flag = false;
    } finally {
        // 归还资源
        if (jedis != null)
            this.returnResource(jedis);
    }
    return flag;
}


public String rpop(String key){
    // 声明一个链接对象
    Jedis jedis = null;
    // 返回标记,默认成功
    String value = null;
    try {
        // 获取资源
        jedis = this.getResource();
        // 资源不为空则执行删除操作 否则返回注入失败
        if (jedis != null)
            value = jedis.rpop(key);
    } finally {
        // 归还资源
        if (jedis != null)
            this.returnResource(jedis);
    }
    return value;
}


public Set<String> getKeys(String keyPrefix){
    logger.info("RedisUtil.getKeys param: " + keyPrefix);
    // jedis对象
    Jedis jedis = null;
    // keys列表
    Set<String> keys = null;
    try {
        // 获取连接
        jedis = this.getResource();
        // 根据前台传过来的规则获取缓存key列表
        if (null != keyPrefix && !"".equals(keyPrefix)) {
            keys = jedis.keys(keyPrefix);
        }
    } catch (SPTException e) {
        logger.error("[JedisUtil.getKeys]:failed. throw e:" + e.getMessage());
    } finally {
        // 使用完毕后将jedis对象归还连接池
        if (jedis != null)
            jedisPool.returnResource(jedis);
    }
    return keys;
}


public Long hset(String key,String field,String value){
    // 声明一个链接对象
    Jedis jedis = null;
    // 获取的键值所对应的值
    Long flag = 0L;
    try {
        // 获取jedis资源
        jedis = this.getResource();
        // 资源不为空,则获取对应的value
        if (jedis != null)
            flag = jedis.hset(key, field, value);
    } finally {
        if (jedis != null)
            this.returnResource(jedis);
    }
    return flag;
}


public String getRedisStrValue(String key){
    // 声明一个链接对象
    Jedis jedis = null;
    // 获取的键值所对应的值
    String value = null;
    try {
        // 获取jedis资源
        jedis = this.getResource();
        // 资源不为空,则获取对应的value
        if (jedis != null)
            value = jedis.get(key);
    } finally {
        if (jedis != null)
            this.returnResource(jedis);
    }
    return value;
}


public boolean hmset(String key,Map<String,String> hash){
    // 声明一个链接对象
    Jedis jedis = null;
    // 返回标记,默认成功
    boolean flag = true;
    try {
        // 获取资源
        jedis = this.getResource();
        // 资源不为空则执行注入操作 否则返回注入失败
        if (jedis != null)
            jedis.hmset(key, hash);
        else
            flag = false;
    } finally {
        // 归还资源
        if (jedis != null)
            this.returnResource(jedis);
    }
    return flag;
}


public boolean srem(String key,String members){
    // 声明一个链接对象
    Jedis jedis = null;
    // 返回标记,默认成功
    boolean flag = true;
    try {
        // 获取资源
        jedis = this.getResource();
        // 资源不为空则执行删除操作 否则返回注入失败
        if (jedis != null)
            jedis.srem(key, members);
        else
            flag = false;
    } finally {
        // 归还资源
        if (jedis != null)
            this.returnResource(jedis);
    }
    return flag;
}


public Set<String> smembers(String key){
    // 声明一个链接对象
    Jedis jedis = null;
    // 获取的键值所对应的值
    Set<String> value = null;
    try {
        // 获取jedis资源
        jedis = this.getResource();
        // 资源不为空,则获取对应的value
        if (jedis != null)
            value = jedis.smembers(key);
    } finally {
        if (jedis != null)
            this.returnResource(jedis);
    }
    return value;
}


public boolean delRedisStrValue(String keys){
    // 声明一个链接对象
    Jedis jedis = null;
    // 返回标记,默认成功
    boolean flag = true;
    try {
        // 获取资源
        jedis = this.getResource();
        // 资源不为空则执行删除操作 否则返回注入失败
        if (jedis != null)
            jedis.del(keys);
        else
            flag = false;
    } finally {
        // 归还资源
        if (jedis != null)
            this.returnResource(jedis);
    }
    return flag;
}


public String getType(String key){
    // 声明一个链接对象
    Jedis jedis = null;
    // 数据类型
    String type = "";
    try {
        // 获取jedis资源
        jedis = this.getResource();
        // 资源不为空,则获取对应的value
        if (jedis != null)
            type = jedis.type(key);
    } finally {
        if (jedis != null)
            this.returnResource(jedis);
    }
    return type;
}


public Set<String> getRedisSorSetValue(String key){
    // 声明一个链接对象
    Jedis jedis = null;
    Set<String> set = new HashSet<String>();
    try {
        // 获取jedis资源
        jedis = this.getResource();
        // 资源不为空,则获取对应的value
        if (jedis != null) {
            set = jedis.zrange(key, 0, -1);
        }
    } finally {
        if (jedis != null) {
            this.returnResource(jedis);
        }
    }
    return set;
}


public boolean rpush(String key,String value){
    // 声明一个链接对象
    Jedis jedis = null;
    // 返回标记,默认成功
    boolean flag = true;
    try {
        // 获取资源
        jedis = this.getResource();
        // 资源不为空则执行删除操作 否则返回注入失败
        if (jedis != null)
            jedis.rpush(key, value);
        else
            flag = false;
    } finally {
        // 归还资源
        if (jedis != null)
            this.returnResource(jedis);
    }
    return flag;
}


public String lpop(String key){
    // 声明一个链接对象
    Jedis jedis = null;
    // 返回标记,默认成功
    String value = null;
    try {
        // 获取资源
        jedis = this.getResource();
        // 资源不为空则执行删除操作 否则返回注入失败
        if (jedis != null)
            value = jedis.lpop(key);
    } finally {
        // 归还资源
        if (jedis != null)
            this.returnResource(jedis);
    }
    return value;
}


public boolean sadd(String key,String members){
    // 声明一个链接对象
    Jedis jedis = null;
    // 返回标记,默认成功
    boolean flag = true;
    try {
        // 获取资源
        jedis = this.getResource();
        // 资源不为空则执行注入操作 否则返回注入失败
        if (jedis != null)
            jedis.sadd(key, members);
        else
            flag = false;
    } finally {
        // 归还资源
        if (jedis != null)
            this.returnResource(jedis);
    }
    return flag;
}


public long decr(String key){
    // 声明一个链接对象
    Jedis jedis = null;
    // 获取的键值所对应的值
    long value = 0;
    try {
        // 获取jedis资源
        jedis = this.getResource();
        // 资源不为空,则获取对应的value
        if (jedis != null)
            value = jedis.decr(key);
    } finally {
        if (jedis != null)
            this.returnResource(jedis);
    }
    return value;
}


public List<String> getRedisListValue(String key){
    // 声明一个链接对象
    Jedis jedis = null;
    // 获取的键值所对应的值
    List<String> listValue = null;
    try {
        // 获取jedis资源
        jedis = this.getResource();
        // 资源不为空,则获取对应的value
        if (jedis != null)
            listValue = jedis.lrange(key, 0, -1);
    } finally {
        if (jedis != null)
            this.returnResource(jedis);
    }
    return listValue;
}


public boolean zadd(String key,double score,String members){
    // 声明一个链接对象
    Jedis jedis = null;
    // 返回标记,默认成功
    boolean flag = true;
    try {
        // 获取资源
        jedis = this.getResource();
        // 资源不为空则执行注入操作 否则返回注入失败
        if (jedis != null)
            jedis.zadd(key, score, members);
        else
            flag = false;
    } finally {
        // 归还资源
        if (jedis != null)
            this.returnResource(jedis);
    }
    return flag;
}


public boolean sismember(String key,String member){
    // 声明一个链接对象
    Jedis jedis = null;
    // 获取的键值所对应的值
    boolean value = false;
    try {
        // 获取jedis资源
        jedis = this.getResource();
        // 资源不为空,则获取对应的value
        if (jedis != null)
            value = jedis.sismember(key, member);
    } finally {
        if (jedis != null)
            this.returnResource(jedis);
    }
    return value;
}


}