package com.xwtec.xwserver.service.tool;
 import java.util.Set;
import com.xwtec.xwserver.pojo.Page;
public interface IRedisService {


public Set<String> getKeys(Page page)
;

public long Ttl(String key)
;

}