package com.zammc.service.goods;
 import com.zammc.domain.goods.GoodsPropertyEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import javax.servlet.http.HttpServletRequest;
public interface GoodsPropertyService {


public Message addGoodsProperty(GoodsPropertyEntity goodsPropertyEntity,HttpServletRequest request)
;

public Message disable(GoodsPropertyEntity goodsPropertyEntity)
;

public Message enable(GoodsPropertyEntity goodsPropertyEntity)
;

public Message delete(GoodsPropertyEntity goodsPropertyEntity)
;

public void queryGoodsPropertyPage(PageBean pageBean)
;

}