package com.xwtec.xwserver.dao.common.impl;
 import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import com.xwtec.xwserver.dao.common.ICityDao;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
@Repository
public class CityDaoImpl implements ICityDao{

@Resource
 private  ICommonDao commonDao;

 private  String QUERY_CITY_LIST;


@Cacheable(value = "cityCache", key = "#containAll")
public Map<String,String> queryCityMap(boolean containAll){
    Map<String, String> result = new LinkedHashMap<String, String>();
    StringBuilder sql = new StringBuilder(QUERY_CITY_LIST);
    if (!containAll) {
        sql.append(" WHERE t.CITY_LEVEL=1");
    }
    sql.append(" ORDER BY t.AREA_CODE");
    List<Map<String, Object>> cityList = commonDao.queryForList(sql.toString());
    for (Map<String, Object> city : cityList) {
        result.put(city.get("areaCode").toString(), city.get("cityName").toString());
    }
    return result;
}


}