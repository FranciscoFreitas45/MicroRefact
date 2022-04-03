package com.circle.service.shopcart;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.shopcart.ShopCart;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
public interface IShopCartService {


public List<Map<String,Object>> queryShopCartList(Page page,int userId)
;

public int countShopCart(int userId)
;

public boolean deleteShopCart(int cartId)
;

public boolean addShopCart(ShopCart shopCart)
;

public double calTotal(String[] cartId)
;

public boolean updateGoodNum(ShopCart shopCart)
;

public Map<String,List<Map<String,Object>>> queryCalShopCartList(int userId,String calCartId)
;

}