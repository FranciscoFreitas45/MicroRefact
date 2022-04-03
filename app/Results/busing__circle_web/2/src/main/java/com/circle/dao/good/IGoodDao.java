package com.circle.dao.good;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.good.GoodBean;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
public interface IGoodDao {


public Map<String,Object> queryGood(String id)
;

public List<Map<String,Object>> queryImageList(String id)
;

public List<Map<String,Object>> queryGoodTypeArgValues(String id)
;

public List<Map<String,Object>> queryGoodList(Page page)
;

public void batchAddBuyNum(List<Map<String,?>> paramListMap)
;

public List<Map<String,Object>> queryCurrentBatchGoodList(Page page,int batchId)
;

public List<Map<String,Object>> queryGoodComment(Page page)
;

public void addBuyNum(String goodId,int buyNum)
;

public List<GoodBean> queryGoods()
;

public List<Map<String,Object>> queryGoodTypeAttrValues(String id)
;

}