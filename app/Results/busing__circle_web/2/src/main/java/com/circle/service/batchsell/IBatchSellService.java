package com.circle.service.batchsell;
 import java.util.List;
import com.circle.pojo.batchsell.BatchSell;
import com.circle.pojo.good.GoodBean;
import com.xwtec.xwserver.exception.SPTException;
public interface IBatchSellService {


public String queryCurrentSellGoodsId(int batchId)
;

public BatchSell queryCurrentBatchSell()
;

public List<GoodBean> queryGoods(Integer id)
;

}