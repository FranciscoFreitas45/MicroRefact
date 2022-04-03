package com.xwtec.xwserver.dao.common.impl;
 import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.dao.common.ISysDicDao;
import com.xwtec.xwserver.exception.SPTException;
@Repository
public class SysDicDaoImpl implements ISysDicDao{

@Resource
 private  ICommonDao commonDao;


@Cacheable(value = "dicCache", key = "#type")
public Map<String,String> queryDicMap(String type){
    Map<String, String> result = new LinkedHashMap<String, String>();
    Map<String, String> paramMap = new HashMap<String, String>();
    String sql = "SELECT t.CODE code,t.NAME name FROM T_SPT_SYS_DIC t WHERE t.STATUS=1 AND t.TYPE=:type ORDER BY t.SORT";
    paramMap.put("type", type);
    List<Map<String, Object>> dicList = commonDao.queryForList(sql, paramMap);
    for (Map<String, Object> dic : dicList) {
        result.put(String.valueOf(dic.get("code")), String.valueOf(dic.get("name")));
    }
    return result;
}


}