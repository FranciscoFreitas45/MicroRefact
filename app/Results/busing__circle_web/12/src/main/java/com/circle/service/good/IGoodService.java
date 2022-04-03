package com.circle.service.good;
 import java.util.List;
import java.util.Map;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
public interface IGoodService {


public Map<String,Object> queryGood(String id)
;

public List<Map<String,Object>> queryImageList(String id)
;

public List<Map<String,Object>> queryGoodTypeArgValues(String id)
;

public List<Map<String,Object>> queryGoodList(Page page)
;

public List<Map<String,Object>> queryCurrentBatchGoodList(Page page,int batchId)
;

public List<Map<String,Object>> queryGoodComment(Page page)
;

public List<Map<String,Object>> queryGoodTypeAttrValues(String id)
;

}