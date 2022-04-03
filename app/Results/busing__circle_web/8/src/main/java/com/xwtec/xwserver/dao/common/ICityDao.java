package com.xwtec.xwserver.dao.common;
 import java.util.Map;
import org.springframework.cache.annotation.Cacheable;
import com.xwtec.xwserver.exception.SPTException;
public interface ICityDao {


@Cacheable(value = "cityCache", key = "#containAll")
public Map<String,String> queryCityMap(boolean containAll)
;

}