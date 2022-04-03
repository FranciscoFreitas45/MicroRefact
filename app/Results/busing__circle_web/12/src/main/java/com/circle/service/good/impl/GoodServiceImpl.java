package com.circle.service.good.impl;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.circle.constant.SystemDict;
import com.circle.dao.good.IGoodDao;
import com.circle.service.good.IGoodService;
import com.circle.utils.NumUtils;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.util.ProUtil;
import com.Interface.IGoodDao;
@Service
@Transactional
public class GoodServiceImpl implements IGoodService{

@Resource
 private  IGoodDao goodDao;

 public  Logger log;


public void converGoodInfo(Map<String,Object> good){
    String sell_price;
    String original_price;
    String cost_price;
    if (good.get("id") == null) {
        return;
    }
    sell_price = good.get("sell_price").toString();
    original_price = good.get("original_price").toString();
    cost_price = good.get("cost_price").toString();
    good.put("type_str", SystemDict.getGoodType(good.get("type_id") + "").getName());
    good.put("image", ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN) + good.get("image"));
    good.put("unit_str", SystemDict.getDict(SystemDict.UNIT, good.get("unit").toString()).getType_name());
    good.put("farm_str", SystemDict.getFarm(good.get("farm_id").toString()).getFarm_name());
    good.put("sell_price", NumUtils.subZeroAndDot(sell_price));
    good.put("sell_price", NumUtils.subZeroAndDot(sell_price));
    good.put("original_price", NumUtils.subZeroAndDot(original_price));
    good.put("cost_price", NumUtils.subZeroAndDot(cost_price));
    good.put("buy_num", good.get("buy_num") == null ? 0 : good.get("buy_num"));
}


@Override
public Map<String,Object> queryGood(String id){
    Map<String, Object> good = goodDao.queryGood(id);
    converGoodInfo(good);
    return good;
}


@Override
public List<Map<String,Object>> queryImageList(String id){
    List<Map<String, Object>> imageList = goodDao.queryImageList(id);
    for (Map<String, Object> map : imageList) {
        map.put("path", ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN) + map.get("img_path"));
    }
    return imageList;
}


@Override
public List<Map<String,Object>> queryGoodTypeArgValues(String id){
    return goodDao.queryGoodTypeArgValues(id);
}


@Override
public List<Map<String,Object>> queryGoodList(Page page){
    List<Map<String, Object>> goodList = goodDao.queryGoodList(page);
    for (Map<String, Object> map : goodList) {
        converGoodInfo(map);
    }
    return goodList;
}


@Override
public List<Map<String,Object>> queryCurrentBatchGoodList(Page page,int batchId){
    List<Map<String, Object>> goodList = goodDao.queryCurrentBatchGoodList(page, batchId);
    for (Map<String, Object> map : goodList) {
        converGoodInfo(map);
    }
    return goodList;
}


@Override
public List<Map<String,Object>> queryGoodComment(Page page){
    return goodDao.queryGoodComment(page);
}


@Override
public List<Map<String,Object>> queryGoodTypeAttrValues(String id){
    return goodDao.queryGoodTypeAttrValues(id);
}


}