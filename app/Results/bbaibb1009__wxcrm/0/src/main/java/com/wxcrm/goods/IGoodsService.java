package com.wxcrm.goods;
 import com.wxcrm.util.Page;
public interface IGoodsService {


public void updGoodsFenlei(WcGoodsFenlei feilei)
;

public void addGoodsFenlei(WcGoodsFenlei fenlei)
;

public Page queryGoodsFeilei(WcGoodsFenlei feilei)
;

public void addGoods(WcGoods goods)
;

public WcGoods getGoodsById(Integer wgsId)
;

public void updGoods(WcGoods goods)
;

public void delGoods(WcGoods goods)
;

public Page queryGoods(WcGoods goods)
;

public void delGoodsFenlei(WcGoodsFenlei fenlei)
;

public WcGoodsFenlei getFeileiById(Integer id)
;

public void addGoodsIn(WcGoodsIn goodsIn)
;

public void addGoodsFeilei(WcGoodsFenlei feilei)
;

}