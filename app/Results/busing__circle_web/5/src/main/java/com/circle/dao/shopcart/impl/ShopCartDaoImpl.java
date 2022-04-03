package com.circle.dao.shopcart.impl;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.circle.constant.CircleTable;
import com.circle.dao.shopcart.IShopCartDAO;
import com.circle.pojo.shopcart.ShopCart;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
import com.Interface.ICommonDao;
@Repository
public class ShopCartDaoImpl implements IShopCartDAO{

@Resource
 private ICommonDao commonDao;

 public  String SAVE_SHOPCART_SQL;

 public  String QUERY_SHOPCART_GOOD_SQL;

 public  String ADD_GOODNUM_SQL;

 public  String UPDATE_GOODNUM_SQL;

 public  String QUERY_COUNT_SHOPCART_SQL;

 public  String DELETE_SHOPCART_SQL;

 public  String QUERY_SHOPCART_LIST_SQL;

 public  String QUERY_CAL_SHOPCART_LIST_SQL;

 public  String TOTAL_SHOPCART;


public Map<String,Object> consShopParam(ShopCart shopCart){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("good_id", shopCart.getGoodId());
    paramMap.put("circle_id", shopCart.getCartId());
    paramMap.put("buy_num", shopCart.getBuyNum());
    paramMap.put("unit_id", shopCart.getUnitId());
    paramMap.put("session_id", shopCart.getSessionId());
    paramMap.put("user_id", shopCart.getUserId());
    paramMap.put("circle_id", shopCart.getCircleId());
    paramMap.put("cart_id", shopCart.getCartId());
    paramMap.put("batchId", shopCart.getBatchId());
    return paramMap;
}


@Override
public List<Map<String,Object>> queryShopCartList(Page page,int userId){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("userId", userId);
    return commonDao.queryForList(QUERY_SHOPCART_LIST_SQL, paramsMap, page);
}


public int queryShopCartGood(ShopCart shopCart){
    Map<String, Object> paramMap = consShopParam(shopCart);
    Map<String, Object> result = commonDao.queryForMap(QUERY_SHOPCART_GOOD_SQL, paramMap);
    return result == null ? 0 : Integer.parseInt(result.get("cart_id").toString());
}


@Override
public int countShopCart(int userId){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("userId", userId);
    return commonDao.queryForInt(QUERY_COUNT_SHOPCART_SQL, paramMap);
}


public String joinString(String[] str,String joinStr){
    StringBuilder sb = new StringBuilder();
    for (String string : str) {
        sb.append(string);
        sb.append(joinStr);
    }
    if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
}


@Override
public boolean deleteShopCart(String cartId){
    StringBuilder sb = new StringBuilder(DELETE_SHOPCART_SQL);
    sb.append(" where cart_id in(" + cartId + ")");
    return commonDao.update(sb.toString(), null) > 0 ? true : false;
}


public boolean addShopCart(ShopCart shopCart){
    Map<String, Object> paramMap = consShopParam(shopCart);
    return commonDao.update(SAVE_SHOPCART_SQL, paramMap) > 0 ? true : false;
}


@Override
public double calTotal(String[] cartId){
    String cartIdStr = joinString(cartId, ",");
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("cartId", cartIdStr);
    StringBuilder sb = new StringBuilder(TOTAL_SHOPCART);
    sb.append(" and s.cart_id in (" + cartIdStr + ")");
    Map<String, Object> result = commonDao.queryForMap(sb.toString(), paramMap);
    if (result == null) {
        return 0;
    }
    double d = Double.parseDouble(result.get("total").toString());
    return d;
}


@Override
public boolean addGoodNum(ShopCart shopCart){
    Map<String, Object> paramMap = consShopParam(shopCart);
    return commonDao.update(ADD_GOODNUM_SQL, paramMap) == 1 ? true : false;
}


public boolean updateGoodNum(ShopCart shopCart){
    Map<String, Object> paramMap = consShopParam(shopCart);
    return commonDao.update(UPDATE_GOODNUM_SQL, paramMap) == 1 ? true : false;
}


@Override
public List<Map<String,Object>> queryCalShopCartList(int userId,String calCartId){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("userId", userId);
    paramsMap.put("calCartId", calCartId);
    StringBuilder sb = new StringBuilder(QUERY_CAL_SHOPCART_LIST_SQL);
    sb.append("and s.cart_id in (");
    sb.append(calCartId);
    sb.append(") order by s.circle_id ");
    return commonDao.queryForList(sb.toString(), paramsMap);
}


}