package com.xwtec.xwserver.controller.tool;
 import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xwtec.xwserver.constant.ConstantBusiKeys;
import com.xwtec.xwserver.constant.ConstantKeys;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.service.tool.IRedisService;
import com.xwtec.xwserver.util.jedis.JedisUtil;
import com.xwtec.xwserver.util.json.JSONObject;
import com.DTO.JSONObject;
@Controller
@RequestMapping("redis")
public class RedisController {

@Resource
 private IRedisService redisService;

 private  Logger logger;

@Resource
 private JedisUtil jedisUtil;


@RequestMapping("init.action")
public ModelAndView init(Page page,ModelAndView modelAndView){
    // 跳转到查询页面
    modelAndView.setViewName("/tool/redis_key_list.jsp");
    return modelAndView;
}


@RequestMapping("querykeyslist.action")
public ModelAndView queryKeysList(Page page,ModelAndView modelAndView){
    logger.info("RedisController.queryKeysList param: " + page.getSearchParam());
    // 根据key模糊查询相对应的缓存keys
    Set<String> keysList = redisService.getKeys(page);
    // 返回前台数据
    modelAndView.addObject("keysList", keysList);
    logger.info("RedisController.queryKeysList returnValue: " + modelAndView);
    // 跳转到查询页面
    modelAndView.setViewName("/tool/redis_key_list.jsp");
    return modelAndView;
}


@RequestMapping("addorupdate.action")
@ResponseBody
public JSONObject addOrUpdate(Page page){
    logger.info("RedisController.addOrUpdate param: " + page.getSearchParam());
    // 用来返回是成功还是失败false失败true成功
    boolean flag = false;
    // 接收返回前台信息
    JSONObject redisDetail = new JSONObject();
    try {
        // 判断前台是否传入数据
        if (null != page.getSearchParam() && !page.getSearchParam().isEmpty()) {
            // 缓存key
            String key = page.getSearchParam().get("key");
            // 缓存value
            String value = page.getSearchParam().get("value");
            // 缓存时间
            int expireTime = Integer.parseInt(page.getSearchParam().get("expireTime"));
            // 根据缓存时间判断怎么塞到缓存中
            if (expireTime == 0) {
                flag = jedisUtil.setRedisStrValue(key, value);
            } else {
                flag = jedisUtil.setRedisStrValue(key, value, expireTime * 60);
            }
        }
        // 成功
        redisDetail.put("flag", flag);
        // 成功原因
        redisDetail.put("desc", ConstantKeys.WebKey.SUCCESS_MSG);
    } catch (Exception e) {
        // 失败
        redisDetail.put("flag", false);
        // 失败原因
        redisDetail.put("desc", e.getMessage());
        logger.error("RedisController.addOrUpdate error:" + e.getMessage());
    }
    logger.info("RedisController.addOrUpdate returnValue:" + redisDetail);
    // 返回查询到的数据
    return redisDetail;
}


@RequestMapping("delredis.action")
@ResponseBody
public JSONObject delRedis(Page page){
    logger.info("RedisController.delRedis param: " + page.getSearchParam());
    // 判断是否删除成功
    boolean flag = false;
    // 接收返回前台信息
    JSONObject redisDetail = new JSONObject();
    try {
        // 判断前台是否传入数据
        if (null != page.getSearchParam() && !page.getSearchParam().isEmpty()) {
            // 缓存key
            String key = page.getSearchParam().get("key");
            // 判断是一个还是多个缓存key
            if (null != key && key.contains(",")) {
                // 把获取过来的keys分割成数组
                String[] keys = key.substring(0, key.length() - 1).split(",");
                // 删除缓存
                flag = jedisUtil.delRedisStrValue(keys);
            } else {
                // 删除缓存
                flag = jedisUtil.delRedisStrValue(key);
            }
        }
        // 成功
        redisDetail.put("flag", flag);
        // 成功原因
        redisDetail.put("desc", ConstantKeys.WebKey.SUCCESS_MSG);
    } catch (Exception e) {
        // 失败
        redisDetail.put("flag", flag);
        // 失败原因
        redisDetail.put("desc", e.getMessage());
        logger.error("RedisController.delRedis error:" + e.getMessage());
    }
    logger.info("RedisController.delRedis returnValue:" + redisDetail);
    // 返回数据
    return redisDetail;
}


@RequestMapping("getkeyinfo.action")
@ResponseBody
public JSONObject getKeyInfo(Page page){
    logger.info("RedisController.getKeyInfo param: " + page.getSearchParam());
    JSONObject redisDetail = new JSONObject();
    try {
        // 判断前台是否传入数据
        if (null != page.getSearchParam() && !page.getSearchParam().isEmpty()) {
            // 获取缓存key
            String key = page.getSearchParam().get("key");
            // 数据类型
            String type = jedisUtil.getType(key);
            if (type.equals("string")) {
                // 字符串(Strings)
                // 根据缓存key获取缓存详情
                String val = jedisUtil.getRedisStrValue(key);
                // 获取根据缓存key获取缓存详情
                redisDetail.put("value", val);
            } else if (type.equals("list")) {
                // 列表(Lists)
                List<String> val = jedisUtil.getRedisListValue(key);
                // 获取根据缓存key获取缓存详情
                redisDetail.put("value", val);
            } else if (type.equals("set")) {
                // 集合(Sets)
                Set<String> val = jedisUtil.smembers(key);
                // 获取根据缓存key获取缓存详情
                redisDetail.put("value", val);
            } else if (type.equals("zset")) {
                // 有序集合(Sorted Sets)
                Set<String> val = jedisUtil.getRedisSorSetValue(key);
                // 获取根据缓存key获取缓存详情
                redisDetail.put("value", val);
            } else if (type.equals("hash")) {
                // 哈希(Hashes)
                List<String> val = jedisUtil.getRedisHashValue(key);
                // 获取根据缓存key获取缓存详情
                redisDetail.put("value", val);
            }
            // 缓存key
            redisDetail.put("key", key);
            // 缓存时间分钟为单位
            redisDetail.put("expireTime", (int) redisService.Ttl(key) / 60);
            // 成功
            redisDetail.put("status", ConstantKeys.WebKey.SUCCESS);
            // 成功原因
            redisDetail.put("respCode", ConstantKeys.WebKey.SUCCESS_MSG);
        } else {
            // 失败
            redisDetail.put("status", ConstantKeys.WebKey.FAIL_NUMBER);
            // 失败原因
            redisDetail.put("respCode", ConstantBusiKeys.Tool.FAILED_MSG);
        }
    } catch (Exception e) {
        // 失败
        redisDetail.put("status", ConstantKeys.WebKey.FAIL_NUMBER);
        // 失败原因
        redisDetail.put("respCode", e.getMessage());
        logger.error("RedisController.getKeyInfo error:" + e.getMessage());
    }
    logger.info("RedisController.getKeyInfo returnValue:" + redisDetail);
    // 返回查询到的数据
    return redisDetail;
}


}