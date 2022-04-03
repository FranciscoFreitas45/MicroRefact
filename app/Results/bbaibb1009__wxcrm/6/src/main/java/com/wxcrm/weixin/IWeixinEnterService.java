package com.wxcrm.weixin;
 import java.util.List;
import java.util.Map;
import com.wxcrm.member.LzWeiMember;
import com.wxcrm.util.Page;
public interface IWeixinEnterService {


public void addWeiEnterOrder(LzWeiEnterOrder order)
;

public void addWeiEnterCust(LzWeiEnterCust enterCust)
;

public void updWeiEnterCust(LzWeiEnterCust enterCust)
;

public void delWeiEnterCust(String[] wetIds)
;

public void delGqByWeiAdmin(Integer gqId)
;

public LzWeiEnterCust getWeiEnterCustById(Integer wetId)
;

public List<Map<String,Object>> queryGqEnterList(Integer enterId)
;

public Page queryWeiEnterOrder(LzWeiEnterOrder order)
;

public void delWeiEnterOrder(String[] weoIds)
;

public List<Map<String,Object>> queryWeiEnterOrderList(LzWeiEnterOrder order)
;

public LzWeiEnterCust getWeiEnterCustByOpenId(String openId)
;

}