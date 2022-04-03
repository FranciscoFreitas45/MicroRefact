package com.wxcrm.weixin;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import com.wxcrm.sys.WcAdmin;
import com.wxcrm.util.Page;
import com.wxcrm.website.WcWebsite;
import com.wxcrm.weixin.pojo.LzWeiAccesstoken;
import com.wxcrm.weixin.pojo.LzWeiJsapiTicket;
public interface IWeixinService {


public Page queryWeixinEnter(LzWeiEnter weiEnter)
;

public void addWatcher(LzWeiWatcher watcher)
;

public List<LzWeiEnter> queryWeixinEnterList()
;

public LzWeiEnter getWeiEnterById(Integer weiEnterId)
;

public List<Map<String,Object>> queryWeiEnterList()
;

public boolean checkAppExsit(LzWeiEnter weiEnter)
;

public LzWeiAccesstoken getCurrentAccessToken(Integer wecId)
;

public void initErrorCode()
;

public String updAccessToken(Integer wecId)
;

public LzWeiEnter getWeiEnterByAdminId(Integer wadId)
;

public Page queryErrorCode(LzWeiErrorcode code)
;

public void deleteWeiEnter(String[] wecIds)
;

public String getNextOpenId()
;

public String updJsApiTicket(Integer wecId)
;

public Integer updWeixinWatcher(String next_OpenId,HttpSession session,Integer wecId,String appId)
;

public void sendMsg(String openId,String msg,Integer wecId)
;

public Date getExpireDate()
;

public void updWatcher(LzWeiWatcher watcher)
;

public Page queryJsApiTicket(LzWeiJsapiTicket tikcet)
;

public LzWeiEnter getWeiEnterByAppId(String appId)
;

public void addWeiEnter(LzWeiEnter weiEnter)
;

public Page queryWebSite(WcWebsite website)
;

public LzWeiWatcher getWatcher(Integer wacId)
;

public void updWeiEnter(LzWeiEnter weiEnter)
;

public String getCurrentAccessTokenStr(Integer wecId)
;

public String getCurtAccTokenStrByApp(String appId)
;

public Page queryWatcher(LzWeiWatcher watcher)
;

public Page queryAccessToken(LzWeiAccesstoken token)
;

public LzWeiJsapiTicket getCurrentTikcet(Integer wecId)
;

public LzWeiWatcher getWatcherByOpenId(String openidStr,Integer wecId)
;

public void addKeyWordsByEnter(LzWeiEnter weiEnter,String[] keyWrds,String[] keyWrdMsgIds,WcAdmin admin)
;

}