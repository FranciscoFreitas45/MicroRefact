package com.circle.dao.batchsell.impl;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.circle.dao.batchsell.IBatchSellDao;
import com.circle.pojo.batchsell.BatchSell;
import com.circle.pojo.batchsell.BatchSellGood;
import com.circle.pojo.good.GoodBean;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.Interface.ICommonDao;
@Repository
public class BatchSellDaoImpl implements IBatchSellDao{

 public  String QUERY_BATCHSELL_LIST_SQL;

 public  String QUERY_CURRENT_BATCHSELL_SQL;

 public  String QUERY_CUURENT_SELL_GOODSID;

 private  String QUERY_BATCHSELL;

 private  String QUERY_BATCHSELL_GOODS;

@Resource
 private ICommonDao commonDao;


public BatchSell queryBatchSell(Integer id){
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("id", id == null || id.equals("") ? 0 : id);
    BatchSell batchSell = null;
    try {
        batchSell = commonDao.queryForObject(QUERY_BATCHSELL, params, BatchSell.class);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return batchSell == null ? new BatchSell() : batchSell;
}


public String queryCurrentSellGoodsId(int batchId){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("batchId", batchId);
    List<Map<String, Object>> cuurentSellGoodsId = commonDao.queryForList(QUERY_CUURENT_SELL_GOODSID, paramsMap);
    StringBuilder sb = new StringBuilder();
    for (Map<String, Object> map : cuurentSellGoodsId) {
        sb.append(map.get("good_id").toString()).append(",");
    }
    return sb.toString();
}


@Override
public BatchSell queryCurrentBatchSell(){
    return commonDao.queryForObject(QUERY_CURRENT_BATCHSELL_SQL, BatchSell.class);
}


public List<BatchSellGood> queryBatchSellGoods(Integer batchId){
    List<BatchSellGood> list = new ArrayList<BatchSellGood>();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("batchId", batchId);
    try {
        list = commonDao.queryForList(QUERY_BATCHSELL_GOODS, params, BatchSellGood.class);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return list;
}


public List<GoodBean> queryGoods(Integer batchId){
    List<GoodBean> list = new ArrayList<GoodBean>();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("batchId", batchId);
    try {
        list = commonDao.queryForList(QUERY_BATCHSELL_GOODS, params, GoodBean.class);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return list;
}


}