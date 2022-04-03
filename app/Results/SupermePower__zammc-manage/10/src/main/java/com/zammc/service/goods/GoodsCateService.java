package com.zammc.service.goods;
 import com.zammc.domain.goods.GoodsCateEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
public interface GoodsCateService {


public GoodsCateEntity queryGoodsCateById(GoodsCateEntity goodsCateEntity)
;

public void forbiddenCate(GoodsCateEntity goodsCateEntity)
;

public void queryGoodsCatePage(GoodsCateEntity goodsCateEntity,PageBean pageBean)
;

public Message addGoodsCate(GoodsCateEntity goodsCateEntity,HttpServletRequest request)
;

public List<GoodsCateEntity> queryCateList()
;

public void startUsingCate(GoodsCateEntity goodsCateEntity)
;

public void deleteGoodsCate(GoodsCateEntity goodsCateEntity)
;

public Message editGoodsCate(GoodsCateEntity goodsCateEntity,HttpServletRequest request)
;

}