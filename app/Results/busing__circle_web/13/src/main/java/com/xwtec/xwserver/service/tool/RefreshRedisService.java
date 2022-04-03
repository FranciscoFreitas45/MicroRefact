package com.xwtec.xwserver.service.tool;
 import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.xwtec.xwserver.dao.tool.RefreshRedisDao;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.tool.CommonNews;
import com.xwtec.xwserver.util.jedis.JedisUtil;
import com.xwtec.xwserver.util.json.JSONObject;
@Service("RefreshRedisService")
public class RefreshRedisService {

@Resource
 private RefreshRedisDao refreshDao;

@Resource
 private JedisUtil jedisUtil;

 public  Logger logger;


public List<CommonNews> getCommonNews(){
    return refreshDao.commonNesList();
}


public boolean doAction(List<Map<String,JSONObject>> mapList){
    boolean flag = false;
    for (int i = 0; i < mapList.size(); i++) {
        Map<String, JSONObject> map = mapList.get(i);
        for (Entry<String, JSONObject> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            flag = jedisUtil.setRedisStrValue(key, value);
            if (flag == false) {
                throw new Exception("RefreshRedisService：+插入缓存错误");
            }
        }
    }
    return flag;
}


}