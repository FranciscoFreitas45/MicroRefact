package com.circle.service.shopcart.impl;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.circle.constant.SystemDict;
import com.circle.dao.shopcart.IShopCartDAO;
import com.circle.pojo.batchsell.BatchSell;
import com.circle.pojo.shopcart.ShopCart;
import com.circle.service.batchsell.IBatchSellService;
import com.circle.service.shopcart.IShopCartService;
import com.circle.utils.NumUtils;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.util.CommonUtil;
import com.xwtec.xwserver.util.ProUtil;
import com.Interface.IBatchSellService;
import com.DTO.BatchSell;
@Service
@Transactional
public class ShopCartServiceImpl implements IShopCartService{

 private  Logger logger;

@Resource
 private IShopCartDAO shopCartDao;

@Resource
 private IBatchSellService batchSellService;


public List<Map<String,Object>> queryShopCartList(Page page,int userId){
    List<Map<String, Object>> cartList = shopCartDao.queryShopCartList(page, userId);
    convertShopCartData(cartList);
    return cartList;
}


public void convertShopCartData(List<Map<String,Object>> cartList){
    String sell_price;
    String total;
    for (Map<String, Object> map : cartList) {
        sell_price = map.get("sell_price").toString();
        total = map.get("total").toString();
        map.put("image", ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN) + map.get("image"));
        map.put("unit_str", SystemDict.getDict(SystemDict.UNIT, map.get("unit").toString()).getType_name());
        map.put("sell_price", NumUtils.subZeroAndDot(sell_price));
        map.put("total", NumUtils.subZeroAndDot(total));
    }
}


@Override
public int countShopCart(int userId){
    return shopCartDao.countShopCart(userId);
}


public boolean deleteShopCart(int cartId){
    return shopCartDao.deleteShopCart(cartId + "");
}


@Override
public boolean addShopCart(ShopCart shopCart){
    try {
        boolean flag = false;
        int cart_id = shopCartDao.queryShopCartGood(shopCart);
        if (cart_id != 0) {
            shopCart.setCartId(cart_id);
            flag = shopCartDao.addGoodNum(shopCart);
        } else {
            flag = shopCartDao.addShopCart(shopCart);
        }
        return flag;
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        return false;
    }
}


public double calTotal(String[] cartId){
    return shopCartDao.calTotal(cartId);
}


@Override
public boolean updateGoodNum(ShopCart shopCart){
    return shopCartDao.updateGoodNum(shopCart);
}


public Map<String,List<Map<String,Object>>> queryCalShopCartList(int userId,String calCartId){
    // 当前售卖批次数据
    BatchSell batchSell = batchSellService.queryCurrentBatchSell();
    Map<String, List<Map<String, Object>>> data = new HashMap<String, List<Map<String, Object>>>();
    List<Map<String, Object>> cartList = shopCartDao.queryCalShopCartList(userId, calCartId);
    convertShopCartData(cartList);
    String circle_id;
    List<Map<String, Object>> tempList;
    for (Map<String, Object> map : cartList) {
        // 去掉当前批次不售卖的商品数据
        if (batchSell == null || !(batchSell.getId() + "").equals(map.get("batch_id") + "")) {
            continue;
        }
        circle_id = map.get("circle_id").toString();
        tempList = data.get(circle_id) == null ? new ArrayList<Map<String, Object>>() : data.get(circle_id);
        tempList.add(map);
        data.put(circle_id, tempList);
    }
    return data;
}


}