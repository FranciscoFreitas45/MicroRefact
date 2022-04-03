package com.circle.dao.batchsell;
 import java.util.List;
import com.circle.pojo.batchsell.BatchSell;
import com.circle.pojo.batchsell.BatchSellGood;
import com.circle.pojo.good.GoodBean;
import com.xwtec.xwserver.exception.SPTException;
public interface IBatchSellDao {


public BatchSell queryBatchSell(Integer id)
;

public String queryCurrentSellGoodsId(int batchId)
;

public BatchSell queryCurrentBatchSell()
;

public List<BatchSellGood> queryBatchSellGoods(Integer batchId)
;

public List<GoodBean> queryGoods(Integer batchId)
;

}