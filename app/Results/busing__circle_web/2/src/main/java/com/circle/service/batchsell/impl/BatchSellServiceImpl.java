package com.circle.service.batchsell.impl;
 import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.circle.constant.SystemDict;
import com.circle.dao.batchsell.IBatchSellDao;
import com.circle.pojo.batchsell.BatchSell;
import com.circle.pojo.good.GoodBean;
import com.circle.service.batchsell.IBatchSellService;
import com.xwtec.xwserver.exception.SPTException;
@Service
@Transactional
public class BatchSellServiceImpl implements IBatchSellService{

 public  Logger logger;

@Resource
 private  IBatchSellDao batchSellDao;


@Override
public String queryCurrentSellGoodsId(int batchId){
    return batchSellDao.queryCurrentSellGoodsId(batchId);
}


@Override
public BatchSell queryCurrentBatchSell(){
    return batchSellDao.queryCurrentBatchSell();
}


public List<GoodBean> queryGoods(Integer id){
    List<GoodBean> goods = new ArrayList<GoodBean>();
    if (id == null || id.equals(""))
        return goods;
    try {
        goods = batchSellDao.queryGoods(id);
        GoodBean good = null;
        for (int i = 0; i < goods.size(); i++) {
            good = goods.get(i);
            good.setUnit_str(SystemDict.getDict(SystemDict.UNIT, good.getUnit() + "").getType_name());
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return goods;
}


}