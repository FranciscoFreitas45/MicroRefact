package com.zammc.service.goods;
 import com.zammc.domain.goods.GoodsEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
public interface GoodsService {


public void queryGoodsPage(GoodsEntity goodsEntity,PageBean pageBean)
;

public void shelfGoods(GoodsEntity goodsEntity)
;

public List<GoodsEntity> queryAllNotSingleGoods()
;

public void dismountGoods(GoodsEntity goodsEntity)
;

public void deleteGoods(GoodsEntity goodsEntity)
;

public Message addGoods(GoodsEntity goodsEntity,HttpServletRequest request)
;

}