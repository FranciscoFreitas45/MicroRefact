package com.circle.dao.shopcart;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.shopcart.ShopCart;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
public interface IShopCartDAO {


public List<Map<String,Object>> queryShopCartList(Page page,int userId)
;

public int queryShopCartGood(ShopCart shopCart)
;

public int countShopCart(int userId)
;

public boolean deleteShopCart(String cartId)
;

public boolean addShopCart(ShopCart shopCart)
;

public double calTotal(String[] cartId)
;

public boolean addGoodNum(ShopCart shopCart)
;

public boolean updateGoodNum(ShopCart shopCart)
;

public List<Map<String,Object>> queryCalShopCartList(int userId,String calCartId)
;

}