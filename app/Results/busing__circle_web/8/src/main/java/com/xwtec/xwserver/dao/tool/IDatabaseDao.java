package com.xwtec.xwserver.dao.tool;
 import java.util.List;
import java.util.Map;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
public interface IDatabaseDao {


public List<Map<String,Object>> getDataBySql(Page page)
;

}