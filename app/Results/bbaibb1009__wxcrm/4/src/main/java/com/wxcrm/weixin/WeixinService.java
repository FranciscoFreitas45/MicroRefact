package com.wxcrm.weixin;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.wxcrm.common.IMemcachedService;
import com.wxcrm.common.dao.IHibernateDao;
import com.wxcrm.common.dao.IJdbcDao;
import com.wxcrm.sys.WcAdmin;
import com.wxcrm.util.DateUtil;
import com.wxcrm.util.Page;
import com.wxcrm.website.WcWebsite;
import com.wxcrm.weixin.pojo.LzWeiAccesstoken;
import com.wxcrm.weixin.pojo.LzWeiJsapiTicket;
import com.wxcrm.Interface.IJdbcDao;
import com.wxcrm.Interface.IHibernateDao;
import com.wxcrm.Interface.IMemcachedService;
import com.wxcrm.Interface.IWeixinEnterService;
@Service
@Transactional
public class WeixinService implements IWeixinService{

@Autowired
 private  IJdbcDao jdbcDao;

@Autowired
 private  IHibernateDao hibernateDao;

@Autowired
 private  IMemcachedService memcachedservice;

@Autowired
 private  IWeixinMessageService weixinmsgservice;

@Autowired
 private  IWeixinEnterService weixinenterservice;

@Value("${weixin.watcher_size}")
 private  Integer watcher_size;


public void updUnSubscribeUser(List<Map<String,Object>> list,Integer wecId){
    String sql = "update LZ_WEI_WATCHER set WAC_SUBSCRIBE = '0' where WAC_WEC_ID = ? and WAC_OPENID = ?";
    for (int i = 0; i < list.size(); i++) {
        Map<String, Object> map = list.get(i);
        String OpenId = (String) map.get("WAC_OPENID");
        jdbcDao.update(sql, new Object[] { wecId, OpenId });
    }
}


public void addWatcher(LzWeiWatcher watcher){
    hibernateDao.add(watcher);
}


public Page queryWeixinEnter(LzWeiEnter weiEnter){
    // TODO ��ѯ���е�΢���˺�
    // Ӧ������
    String appName = weiEnter.getWecAppName_Q();
    // appId
    String appId = weiEnter.getWecAppId_Q();
    // ��Ϣ���ܷ�ʽ
    String aesType = weiEnter.getWecAesType_Q();
    // Ӧ������
    String appType = weiEnter.getWecAppType_Q();
    // �ͻ�����
    String cusType = weiEnter.getWecCusType_Q();
    // �˻�����
    String accountType = weiEnter.getWecAccountType_Q();
    // ��ҵ����
    String enterName = weiEnter.getWecEnterName_Q();
    // �̼�Id
    Integer enterId = weiEnter.getWecEnterId();
    List<Object> paraList = new ArrayList<Object>();
    StringBuilder sql = new StringBuilder(" select " + " a.WEC_ID,a.WEC_APP_NAME,a.WEC_APP_ID,a.WEC_APP_SECRET," + " a.WEC_REDERECT_URL,a.WEC_TOKEN,a.WEC_ENCODING_AES_KEY,a.WEC_AES_TYPE," + " a.WEC_APP_TYPE,a.WEC_CUS_TYPE,a.WEC_ACCOUNT_TYPE,a.WEC_ENTER_ID," + " a.WEC_STATUS,a.WEC_DESC,b.WCS_WEBSITE_NAME  " + " from LZ_WEI_ENTER a" + " left join WC_WEBSITE b on a.WEC_ENTER_ID = b.WCS_ID  " + " where 1=1 ");
    StringBuilder sqlCnt = new StringBuilder(" select " + " count(*) " + " from  LZ_WEI_ENTER a " + " left join WC_WEBSITE b on a.WEC_ENTER_ID = b.WCS_ID  " + " where 1=1 ");
    if (enterId != null && enterId > 0) {
        sql.append(" and a.WEC_ENTER_ID = ? ");
        sqlCnt.append(" and a.WEC_ENTER_ID = ? ");
        paraList.add(enterId);
    }
    if (appName != null && appName.length() > 0) {
        sql.append(" and a.WEC_APP_NAME like ? ");
        sqlCnt.append(" and a.WEC_APP_NAME like ? ");
        paraList.add("%" + appName + "%");
    }
    if (appId != null && appId.length() > 0) {
        sql.append(" and a.WEC_APP_ID = ? ");
        sqlCnt.append(" and a.WEC_APP_ID = ? ");
        paraList.add(appId);
    }
    if (aesType != null && aesType.length() > 0) {
        sql.append(" and a.WEC_AES_TYPE = ? ");
        sqlCnt.append(" and a.WEC_AES_TYPE = ? ");
        paraList.add(aesType);
    }
    if (appType != null && appType.length() > 0) {
        sql.append(" and a.WEC_APP_TYPE = ? ");
        sqlCnt.append(" and a.WEC_APP_TYPE = ? ");
        paraList.add(appType);
    }
    if (cusType != null && cusType.length() > 0) {
        sql.append(" and a.WEC_CUS_TYPE = ? ");
        sqlCnt.append(" and a.WEC_CUS_TYPE = ? ");
        paraList.add(cusType);
    }
    if (accountType != null && accountType.length() > 0) {
        sql.append(" and a.WEC_ACCOUNT_TYPE = ? ");
        sqlCnt.append(" and a.WEC_ACCOUNT_TYPE = ? ");
        paraList.add(accountType);
    }
    if (enterName != null && enterName.length() > 0) {
        sql.append(" and b.WCS_WEBSITE_NAME like ? ");
        sqlCnt.append(" and b.WCS_WEBSITE_NAME like ? ");
        paraList.add("%" + enterName + "%");
    }
    Page page = new Page(sql.toString(), sqlCnt.toString(), weiEnter.getCurrentPage(), weiEnter.getPageSize(), paraList.toArray());
    jdbcDao.queryForPage(page);
    return page;
}


public List<LzWeiEnter> queryWeixinEnterList(){
    // TODO Auto-generated method stub
    String hql = " from LzWeiEnter weiEnter";
    return hibernateDao.query(hql);
}


public LzWeiEnter getWeiEnterById(Integer weiEnterId){
    // TODO Auto-generated method stub
    LzWeiEnter weiEnter = hibernateDao.get(LzWeiEnter.class, weiEnterId);
    if (weiEnter.getWecDefaultMsg() != null && weiEnter.getWecDefaultMsg() > 0) {
        LzWeiMessage msgDefault = weixinmsgservice.getLzWeiMessageById(weiEnter.getWecDefaultMsg());
        weiEnter.setWecDefaultMsgDesc(msgDefault.getWmgContent());
    }
    if (weiEnter.getWecSubscribeMsg() != null && weiEnter.getWecSubscribeMsg() > 0) {
        LzWeiMessage msgDefault = weixinmsgservice.getLzWeiMessageById(weiEnter.getWecSubscribeMsg());
        weiEnter.setWecSubscribeMsgDesc(msgDefault.getWmgContent());
    }
    // ��ѯ��ȡ�ؼ��ֻظ����б�
    weiEnter.setListKeyWords(weixinmsgservice.queryKeywordListByWei(weiEnter.getWecId()));
    return weiEnter;
}


public List<Map<String,Object>> queryWeiEnterList(){
    String sql = "select a.WEC_ID,a.WEC_APP_NAME,a.WEC_APP_ID from LZ_WEI_ENTER a ";
    return jdbcDao.queryForList(sql);
}


public void addJsApiTicket(String jsApiTicket,int expires_in,Integer wecId,String appid){
    LzWeiJsapiTicket jsapiTicket = new LzWeiJsapiTicket();
    Date now = new Date();
    now.setTime(now.getTime() + (expires_in * 1000));
    jsapiTicket.setWjtJsapiTicket(jsApiTicket);
    jsapiTicket.setWjtExpiresIn(DateUtil.parseString(now, "yyyy-MM-dd HH:mm:ss"));
    jsapiTicket.setWjtWecId(wecId);
    jsapiTicket.setWjtAppid(appid);
    jsapiTicket.setWjtStatus("1");
    jsapiTicket.setWjtCreatTime(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
    hibernateDao.add(jsapiTicket);
    hibernateDao.flush();
}


public void main(String[] args){
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    map1.put("WAC_OPENID", "123");
    Map<String, Object> map2 = new HashMap<String, Object>();
    map2.put("WAC_OPENID", "321");
    Map<String, Object> map3 = new HashMap<String, Object>();
    map3.put("WAC_OPENID", "444");
    list.add(map1);
    list.add(map2);
    list.add(map3);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("WAC_OPENID", "123");
    System.out.println(list.contains(map));
    System.out.println(list.remove(map));
    System.out.println(list.size());
}


public boolean checkAppExsit(LzWeiEnter weiEnter){
    // TODO ���ںŲ���
    String sql = " select count(*) from LZ_WEI_ENTER a where WEC_APP_ID = ? and WEC_APP_TYPE = ? ";
    Integer cnt = jdbcDao.queryForInt(sql, new Object[] { weiEnter.getWecAppId(), weiEnter.getWecAppType() });
    return cnt > 0;
}


public LzWeiAccesstoken getCurrentAccessToken(Integer wecId){
    LzWeiEnter enter = this.getWeiEnterById(wecId);
    String hql = "from LzWeiAccesstoken a where a.watWecId = ? and a.watStatus  = 1 ";
    List<LzWeiAccesstoken> tokenList = hibernateDao.query(hql, new Object[] { wecId });
    if (tokenList.size() > 0) {
        LzWeiAccesstoken token = tokenList.get(0);
        String tokenStr = token.getWatToken();
        Date expireIn = DateUtil.parseDate(token.getWatExpiresIn(), "yyyy-MM-dd HH:mm:ss");
        if (expireIn.getTime() <= (new Date()).getTime()) {
            tokenStr = this.updAccessToken(wecId);
            List<LzWeiAccesstoken> tokenList1 = hibernateDao.query(hql, new Object[] { wecId });
            token = tokenList1.get(0);
        }
        return token;
    } else {
        this.updAccessToken(wecId);
        List<LzWeiAccesstoken> tokenList1 = hibernateDao.query(hql, new Object[] { wecId });
        return tokenList1.get(0);
    }
}


public void initErrorCode(){
    // TODO Auto-generated method stub
    String errorCode = "[" + "	{\"code\":\"-1\",\"desc\":\"ϵͳ��æ\"},	" + "	{\"code\":\"0\",\"desc\":\"����ɹ�\"}," + "	{\"code\":\"40001\",\"desc\":\"��ȡaccess_tokenʱAppSecret���󣬻���access_token��Ч\"}," + "	{\"code\":\"40002\",\"desc\":\"���Ϸ���ƾ֤����\"}," + "	{\"code\":\"40003\",\"desc\":\"���Ϸ���OpenID\"}," + "	{\"code\":\"40004\",\"desc\":\"���Ϸ���ý���ļ�����\"},	" + "	{\"code\":\"40005\",\"desc\":\"���Ϸ����ļ�����\"}," + "	{\"code\":\"40006\",\"desc\":\"���Ϸ����ļ���С\"}," + "	{\"code\":\"40007\",\"desc\":\"���Ϸ���ý���ļ�id\"}," + "	{\"code\":\"40008\",\"desc\":\"���Ϸ�����Ϣ����\"}," + "	{\"code\":\"40009\",\"desc\":\"���Ϸ���ͼƬ�ļ���С\"}," + "	{\"code\":\"40010\",\"desc\":\"���Ϸ��������ļ���С\"}," + "	{\"code\":\"40011\",\"desc\":\"���Ϸ�����Ƶ�ļ���С\"}," + "	{\"code\":\"40012\",\"desc\":\"���Ϸ�������ͼ�ļ���С\"}," + "	{\"code\":\"40013\",\"desc\":\"���Ϸ���APPID\"}," + "	{\"code\":\"40016\",\"desc\":\"���Ϸ��İ�ť����\"}," + "	{\"code\":\"40017\",\"desc\":\"���Ϸ��İ�ť����\"}," + "	{\"code\":\"40018\",\"desc\":\"���Ϸ��İ�ť���ֳ���\"}," + "	{\"code\":\"40019\",\"desc\":\"���Ϸ��İ�ťKEY����\"}," + "	{\"code\":\"40020\",\"desc\":\"���Ϸ��İ�ťURL����\"}," + "	{\"code\":\"40021\",\"desc\":\"���Ϸ��Ĳ˵��汾��\"}," + "	{\"code\":\"40022\",\"desc\":\"���Ϸ����Ӳ˵�����\"}," + "	{\"code\":\"40023\",\"desc\":\"���Ϸ����Ӳ˵���ť����\"}," + "	{\"code\":\"40024\",\"desc\":\"���Ϸ����Ӳ˵���ť����\"}," + "	{\"code\":\"40025\",\"desc\":\"���Ϸ����Ӳ˵���ť���ֳ���\"}," + "	{\"code\":\"40026\",\"desc\":\"���Ϸ����Ӳ˵���ťKEY����\"}," + "	{\"code\":\"40027\",\"desc\":\"���Ϸ����Ӳ˵���ťURL����\"}," + "	{\"code\":\"40028\",\"desc\":\"���Ϸ����Զ���˵�ʹ���û�\"}," + "	{\"code\":\"40029\",\"desc\":\"���Ϸ���oauth_code\"}," + "	{\"code\":\"40030\",\"desc\":\"���Ϸ���refresh_token\"}," + "	{\"code\":\"40031\",\"desc\":\"���Ϸ���openid�б�\"}," + "	{\"code\":\"40032\",\"desc\":\"���Ϸ���openid�б���\"}," + "	{\"code\":\"40033\",\"desc\":\"���Ϸ��������ַ������ܰ���\\\\uxxxx��ʽ���ַ�\"}," + "	{\"code\":\"40035\",\"desc\":\"���Ϸ��Ĳ���\"}," + "	{\"code\":\"40038\",\"desc\":\"���Ϸ��������ʽ\"}," + "	{\"code\":\"40039\",\"desc\":\"���Ϸ���URL����\"},	" + "	{\"code\":\"40050\",\"desc\":\"���Ϸ��ķ���id\"}," + "	{\"code\":\"40051\",\"desc\":\"�������ֲ��Ϸ�\"}," + "	{\"code\":\"41001\",\"desc\":\"ȱ��access_token����\"}," + "	{\"code\":\"41002\",\"desc\":\"ȱ��appid����\"}," + "	{\"code\":\"41003\",\"desc\":\"ȱ��refresh_token����\"}," + "	{\"code\":\"41004\",\"desc\":\"ȱ��secret����\"}," + "	{\"code\":\"41005\",\"desc\":\"ȱ�ٶ�ý���ļ�����\"}," + "	{\"code\":\"41006\",\"desc\":\"ȱ��media_id����\"}," + "	{\"code\":\"41007\",\"desc\":\"ȱ���Ӳ˵�����\"}," + "	{\"code\":\"41008\",\"desc\":\"ȱ��oauth code\"}," + "	{\"code\":\"41009\",\"desc\":\"ȱ��openid\"}," + "	{\"code\":\"42001\",\"desc\":\"access_token��ʱ\"}," + "	{\"code\":\"42002\",\"desc\":\"refresh_token��ʱ\"}," + "	{\"code\":\"42003\",\"desc\":\"oauth_code��ʱ\"}," + "	{\"code\":\"43001\",\"desc\":\"��ҪGET����\"}," + "	{\"code\":\"43002\",\"desc\":\"��ҪPOST����\"}," + "	{\"code\":\"43003\",\"desc\":\"��ҪHTTPS����\"}," + "	{\"code\":\"43004\",\"desc\":\"��Ҫ�����߹�ע\"}," + "	{\"code\":\"43005\",\"desc\":\"��Ҫ���ѹ�ϵ\"}," + "	{\"code\":\"44001\",\"desc\":\"��ý���ļ�Ϊ��\"}," + "	{\"code\":\"44002\",\"desc\":\"POST�����ݰ�Ϊ��\"}," + "	{\"code\":\"44003\",\"desc\":\"ͼ����Ϣ����Ϊ��\"}," + "	{\"code\":\"44004\",\"desc\":\"�ı���Ϣ����Ϊ��\"}," + "	{\"code\":\"45001\",\"desc\":\"��ý���ļ���С��������\"}," + "	{\"code\":\"45002\",\"desc\":\"��Ϣ���ݳ�������\"}," + "	{\"code\":\"45003\",\"desc\":\"�����ֶγ�������\"}," + "	{\"code\":\"45004\",\"desc\":\"�����ֶγ�������\"}," + "	{\"code\":\"45005\",\"desc\":\"�����ֶγ�������\"}," + "	{\"code\":\"45006\",\"desc\":\"ͼƬ�����ֶγ�������\"}," + "	{\"code\":\"45007\",\"desc\":\"��������ʱ�䳬������\"}," + "	{\"code\":\"45008\",\"desc\":\"ͼ����Ϣ��������\"}," + "	{\"code\":\"45009\",\"desc\":\"�ӿڵ��ó�������\"}," + "	{\"code\":\"45010\",\"desc\":\"�����˵�������������\"}," + "	{\"code\":\"45015\",\"desc\":\"�ظ�ʱ�䳬������\"}," + "	{\"code\":\"45016\",\"desc\":\"ϵͳ���飬�������޸�\"}," + "	{\"code\":\"45017\",\"desc\":\"�������ֹ���\"}," + "	{\"code\":\"45018\",\"desc\":\"����������������\"}," + "	{\"code\":\"46001\",\"desc\":\"������ý������\"}," + "	{\"code\":\"46002\",\"desc\":\"�����ڵĲ˵��汾\"}," + "	{\"code\":\"46003\",\"desc\":\"�����ڵĲ˵�����\"}," + "	{\"code\":\"46004\",\"desc\":\"�����ڵ��û�\"}," + "	{\"code\":\"47001\",\"desc\":\"����JSON/XML���ݴ���\"}," + "	{\"code\":\"48001\",\"desc\":\"api����δ��Ȩ\"}," + "	{\"code\":\"50001\",\"desc\":\"�û�δ��Ȩ��api\"}" + "	]";
    JSONArray array = JSONArray.fromString(errorCode);
    String sql = "insert into LZ_WEI_ERRORCODE (WAE_CODE,WAE_DESC) values (?,?) ";
    for (int i = 0; i < array.length(); i++) {
        JSONObject obj = (JSONObject) array.get(i);
        String code = (String) obj.get("code");
        String desc = (String) obj.get("desc");
        jdbcDao.add(sql, new Object[] { code, desc });
    }
}


public String updAccessToken(Integer wecId){
    try {
        LzWeiEnter enter = this.getWeiEnterById(wecId);
        JSONObject jsonObject = com.oilchem.weixin.accesstoken.AccessTokenUtil.getAccessTokenJson(enter.getWecAppId(), enter.getWecAppSecret());
        String accessToken = jsonObject.getString("access_token");
        int expires_in = jsonObject.getInt("expires_in");
        if (accessToken != null && accessToken.length() > 0) {
            disabledAccessToken(wecId);
            addAccessToken(accessToken, expires_in, wecId, enter.getWecAppId());
            return accessToken;
        } else {
            return null;
        }
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


public LzWeiEnter getWeiEnterByAdminId(Integer wadId){
    // TODO Auto-generated method stub
    LzWeiEnter enter = null;
    String sql = "select b.WEC_ID from WC_WEBSITE a join LZ_WEI_ENTER b on a.WCS_APP_ID = b.WEC_APP_ID join WC_ADMIN c on c.WAD_ID = a.WCS_ADMIN_ID where a.WCS_ADMIN_ID = ? limit 1";
    List<Map<String, Object>> list = jdbcDao.queryForList(sql, new Object[] { wadId });
    if (list.size() > 0) {
        Map<String, Object> map = list.get(0);
        Integer wecId = (Integer) map.get("WEC_ID");
        enter = getWeiEnterById(wecId);
    }
    return enter;
}


@Transactional(readOnly = true)
public Page queryErrorCode(LzWeiErrorcode code){
    // TODO Auto-generated method stub
    StringBuilder sql = new StringBuilder("select WAE_CODE,WAE_DESC from LZ_WEI_ERRORCODE ");
    StringBuilder sqlCnt = new StringBuilder(" select count(*) from LZ_WEI_ERRORCODE ");
    List<Object> paraList = new ArrayList<Object>();
    Page page = new Page(sql.toString(), sqlCnt.toString(), code.getCurrentPage(), code.getPageSize(), paraList.toArray());
    jdbcDao.queryForPage(page);
    return page;
}


public void deleteWeiEnter(String[] wecIds){
    // TODO Auto-generated method stub
    String wecIdStr = StringUtils.arrayToCommaDelimitedString(wecIds);
    // ɾ�����еĹؼ���
    String sql0 = "delete from LZ_WEI_KEYWORD_MESSAGE where WKG_WEC_ID in (" + wecIdStr + ")";
    // ɾ�����еĹ�ע��
    String sql1 = "delete from LZ_WEI_WATCHER  where WAC_WEC_ID in (" + wecIdStr + ")";
    // ɾ�����еĲ˵�
    String sql2 = "delete from LZ_WEI_MENU  where WMU_WEC_ID in (" + wecIdStr + ")";
    // ɾ�����е�AccessToken
    String sql3 = "delete from LZ_WEI_ACCESSTOKEN  where WAT_WEC_ID in (" + wecIdStr + ")";
    // ɾ����Ӧ����ҵ΢��
    String sql = "delete from LZ_WEI_ENTER where WEC_ID in ( " + wecIdStr + ")";
    jdbcDao.delete(sql0);
    jdbcDao.delete(sql1);
    jdbcDao.delete(sql2);
    jdbcDao.delete(sql3);
    jdbcDao.delete(sql);
}


public String getNextOpenId(){
    String sql = "select top 1 WAC_OPENID from LZ_WEI_WATCHER a order by a.WAC_ID desc";
    List<Map<String, Object>> list = jdbcDao.queryForList(sql);
    if (list.size() > 0) {
        Map<String, Object> map = list.get(0);
        return (String) map.get("WAC_OPENID");
    } else {
        return "";
    }
}


public String updJsApiTicket(Integer wecId){
    try {
        LzWeiEnter enter = this.getWeiEnterById(wecId);
        LzWeiAccesstoken lzAccessToken = getCurrentAccessToken(wecId);
        // ����Ҫ������ȡ�����µ�accessToken
        String accessToken = lzAccessToken.getWatToken();
        JSONObject jsonObject = com.oilchem.weixin.jsapi.JsApiTicketUtil.getJsApiTicketJson(accessToken);
        String jsApiTicket = jsonObject.getString("ticket");
        int expires_in = jsonObject.getInt("expires_in");
        if (jsApiTicket != null && jsApiTicket.length() > 0) {
            disabledJsApiTicket(wecId);
            addJsApiTicket(jsApiTicket, expires_in, wecId, enter.getWecAppId());
            return jsApiTicket;
        } else {
            return null;
        }
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


public Integer updWeixinWatcher(String next_OpenId,HttpSession session,Integer wecId,String appId){
    // TODO ��Զ�˸���΢�ſͻ��˵Ĺ�ע�û���Ϣ
    try {
        String access_token = this.getCurrentAccessTokenStr(wecId);
        // String url = "https://api.weixin.qq.com/cgi-bin/user/get";
        System.out.println("��ǰ��nextOpenId:" + next_OpenId);
        // Map<String,String> map = new HashMap<String, String>();
        // map.put("access_token", access_token);
        // map.put("next_openid", next_OpenId);
        // String watcherString = HttpClientUtil.doPost(url, map,"UTF-8");
        // JSONObject jsonToken = JSONObject.fromString(watcherString);
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + access_token + "&next_openid=" + next_OpenId;
        // JSONObject jsonToken = JSONObject.fromString(watcherString);
        JSONObject jsonToken = com.oilchem.weixin.http.HttpUtil.httpRequestJson(url, "GET", null);
        if (jsonToken != null && jsonToken.has("total") && jsonToken.getString("total") != null) {
            Integer total = Integer.parseInt(jsonToken.getString("total"));
            if (total > 0) {
                if (jsonToken.has("data")) {
                    JSONObject dataObject = jsonToken.getJSONObject("data");
                    JSONArray openIdArray = dataObject.getJSONArray("openid");
                    // �������50��
                    int size = total <= watcher_size ? total : watcher_size;
                    // �����ǰȡ����������������50�� �Ͱ��յ�ǰȡ�����������и���
                    if (openIdArray.length() < watcher_size)
                        size = openIdArray.length();
                    System.out.println("openIdArray:" + openIdArray.toString());
                    System.out.println("total:" + total);
                    System.out.println("size:" + size);
                    List<Map<String, Object>> list = this.queryWatcherLocalList(wecId);
                    for (int i = 0; i < size; i++) {
                        // ��ȡOpenId
                        String openidStr = openIdArray.getString(i);
                        LzWeiWatcher watcher = this.getWatcherByOpenId(openidStr, wecId);
                        if (watcher == null) {
                            watcher = new LzWeiWatcher();
                            watcher.setWacOpenid(openidStr);
                            watcher.setWacStatus("0");
                            watcher.setWacAppid(appId);
                            watcher.setWacWecId(wecId);
                            this.addWatcher(watcher);
                        }
                        Map<String, Object> userMap = this.querUserInfo(openidStr, wecId);
                        if (userMap != null) {
                            watcher.setWacSubscribe(Integer.parseInt((String) userMap.get("subscribe")));
                            watcher.setWacNickName((String) userMap.get("nickname"));
                            watcher.setWacSex(userMap.get("sex").toString());
                            watcher.setWacLanguage((String) userMap.get("language"));
                            watcher.setWacCity((String) userMap.get("city"));
                            watcher.setWacProvince((String) userMap.get("province"));
                            watcher.setWacCountry((String) userMap.get("country"));
                            watcher.setWacHeadImgUrl((String) userMap.get("headimgurl"));
                            watcher.setWacStatus("1");
                            Date subscribeTime = new Date();
                            subscribeTime.setTime((Long.parseLong((String) userMap.get("subscribe_time"))) * (long) 1000);
                            watcher.setWacSubscribeTime(DateUtil.parseString(subscribeTime, "yyyy-MM-dd HH:mm:ss"));
                            this.updWatcher(watcher);
                        } else {
                            continue;
                        }
                        if (i == size - 1 && session != null) {
                            session.setAttribute("curNextOpenId", openidStr);
                        }
                        Map<String, Object> map1 = new HashMap<String, Object>();
                        map1.put("WAC_OPENID", openidStr);
                        // ���Ѿ��������openid�ӱ����б���ȥ��
                        if (list.contains(map1)) {
                            list.remove(map1);
                        }
                    }
                    // ��������б��л���û�����openId֤����Щ�����Ѿ�ȡ����ע�˵��û�
                    if (list.size() > 0) {
                        // �����ǵ�״̬��עΪȡ����ע
                        this.updUnSubscribeUser(list, wecId);
                    }
                    return size;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    } catch (JSONException e) {
        // TODO: handle exception
        e.printStackTrace();
        return 0;
    } catch (Exception e1) {
        e1.printStackTrace();
        return 0;
    }
}


public void sendMsg(String openId,String msg,Integer wecId){
    // TODO ��΢���û�������Ϣ
    try {
        String accesstoken = this.getCurrentAccessTokenStr(wecId);
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accesstoken;
        JSONObject msgObj = new JSONObject();
        msgObj.put("touser", openId);
        msgObj.put("msgtype", "text");
        JSONObject textObj = new JSONObject();
        textObj.put("content", new String(msg.getBytes("utf-8"), "ISO8859_1"));
        msgObj.put("text", textObj);
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        StringEntity s = new StringEntity(msgObj.toString());
        s.setContentEncoding("UTF-8");
        // s.setContentType("application/json");
        post.setEntity(s);
        HttpResponse res = client.execute(post);
        if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = res.getEntity();
            String result = EntityUtils.toString(res.getEntity(), "utf-8").trim();
            System.out.println(result);
        // String charset = EntityUtils.getContentCharSet(entity);
        // System.out.println(new InputStreamReader(entity.getContent(),"utf-8").toString());
        // response = new JSONObject(new JSONTokener(new InputStreamReader(entity.getContent(),charset)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public Date getExpireDate(){
    // TODO Auto-generated method stub
    String sql = "select top 1 WAT_EXPIRES_IN from LZ_WEI_ACCESSTOKEN order by WAT_EXPIRES_IN desc ";
    List<Map<String, Object>> list = jdbcDao.queryForList(sql);
    if (list.size() > 0) {
        Map<String, Object> map = list.get(0);
        Date expire = (Date) map.get("WAT_EXPIRES_IN");
        return expire;
    } else {
        return null;
    }
}


public Map<String,Object> querUserInfo(String openId,Integer wecId){
    Map<String, Object> resMap = new HashMap<String, Object>();
    String access_token = this.getCurrentAccessTokenStr(wecId);
    String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + access_token + "&openid=" + openId + "&lang=zh_CN";
    // Map<String,String> map = new HashMap<String, String>();
    // map.put("access_token", access_token);
    // map.put("openid", openId);
    // map.put("lang", "zh_CN");
    // String watcherString = HttpClientUtil.doPost(url,map,"UTF-8");
    // WeixinUtil.httpRequest(url, "GET", "");
    // JSONObject jsonToken = JSONObject.fromString(watcherString);
    JSONObject jsonToken = com.oilchem.weixin.http.HttpUtil.httpRequestJson(url, "GET", null);
    if (jsonToken != null) {
        System.out.println(jsonToken.toString());
        if (jsonToken.has("subscribe")) {
            String subscribe = jsonToken.getString("subscribe");
            if (subscribe != null && subscribe.equals("1")) {
                resMap.put("subscribe", jsonToken.getString("subscribe"));
                resMap.put("openid", jsonToken.getString("openid"));
                resMap.put("nickname", jsonToken.getString("nickname"));
                resMap.put("sex", jsonToken.getInt("sex"));
                resMap.put("language", jsonToken.getString("language"));
                resMap.put("city", jsonToken.getString("city"));
                resMap.put("province", jsonToken.getString("province"));
                resMap.put("country", jsonToken.getString("country"));
                resMap.put("headimgurl", jsonToken.getString("headimgurl"));
                resMap.put("subscribe_time", jsonToken.getString("subscribe_time"));
                return resMap;
            } else {
                return null;
            }
        } else {
            return null;
        }
    } else {
        return null;
    }
}


public void updWatcher(LzWeiWatcher watcher){
    hibernateDao.update(watcher);
}


@Transactional(readOnly = true)
public Page queryJsApiTicket(LzWeiJsapiTicket tikcet){
    // TODO Auto-generated method stub
    String wecid_Q = tikcet.getWatWecId_Q();
    StringBuilder sql = new StringBuilder("select " + " a.WJT_ID," + " a.WJT_JSAPI_TICKET," + " a.WJT_EXPIRES_IN," + " a.WJT_CREAT_TIME," + " a.WJT_WEC_ID," + " a.WJT_APPID," + " b.WEC_APP_NAME," + " a.WJT_STATUS " + " from LZ_WEI_JSAPI_TICKET a left join LZ_WEI_ENTER b on a.WJT_WEC_ID = b.WEC_ID where 1=1 ");
    StringBuilder sqlCnt = new StringBuilder(" select count(*) " + " from LZ_WEI_JSAPI_TICKET a left join LZ_WEI_ENTER b on a.WJT_WEC_ID = b.WEC_ID where 1=1 ");
    List<Object> paraList = new ArrayList<Object>();
    if (wecid_Q != null && wecid_Q.length() > 0) {
        sql.append(" and b.WEC_ID = ? ");
        sqlCnt.append(" and b.WEC_ID = ? ");
        paraList.add(wecid_Q);
    }
    Page page = new Page(sql.toString(), sqlCnt.toString(), tikcet.getCurrentPage(), tikcet.getPageSize(), paraList.toArray());
    jdbcDao.queryForPage(page);
    return page;
}


public LzWeiEnter getWeiEnterByAppId(String appId){
    // TODO ����appId��ȡ��ǰ��΢���˺�
    String hql = " from LzWeiEnter enter where enter.wecAppId = ? and enter.wecAppType = '1' ";
    List<LzWeiEnter> list1 = hibernateDao.query(hql, new Object[] { appId });
    if (list1.size() > 0) {
        return list1.get(0);
    } else {
        return null;
    }
}


public void addWeiEnter(LzWeiEnter weiEnter){
    // TODO ��ӹ��ں�
    weiEnter.setWecRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
    hibernateDao.add(weiEnter);
}


public Page queryWebSite(WcWebsite website){
    // TODO Auto-generated method stub
    String websiteName = website.getWcsWebSiteName();
    List<Object> paraList = new ArrayList<Object>();
    StringBuilder sql = new StringBuilder(" select distinct " + " WCS_ID," + " WCS_WEBSITE_NAME," + " WCS_ADMIN_ID " + " from WC_WEBSITE a where 1=1 ");
    StringBuilder sqlCnt = new StringBuilder(" select " + " count(*) " + " from WC_WEBSITE a where 1=1 ");
    if (websiteName != null && websiteName.length() > 0) {
        sql.append(" and a.WCS_WEBSITE_NAME like ? ");
        sqlCnt.append(" and a.WCS_WEBSITE_NAME like ? ");
        paraList.add("%" + websiteName + "%");
    }
    Page page = new Page(sql.toString(), sqlCnt.toString(), website.getCurrentPage(), website.getPageSize(), paraList.toArray());
    jdbcDao.queryForPage(page);
    return page;
}


public LzWeiWatcher getWatcher(Integer wacId){
    // TODO Auto-generated method stub
    return hibernateDao.get(LzWeiWatcher.class, wacId);
}


public String getCurrentAccessTokenStr(Integer wecId){
    String hql = "from LzWeiAccesstoken a where a.watWecId = ? and a.watStatus  = 1 ";
    List<LzWeiAccesstoken> tokenList = hibernateDao.query(hql, new Object[] { wecId });
    if (tokenList.size() > 0) {
        LzWeiAccesstoken token = tokenList.get(0);
        String tokenStr = token.getWatToken();
        Date expireIn = DateUtil.parseDate(token.getWatExpiresIn(), "yyyy-MM-dd HH:mm:ss");
        if (expireIn.getTime() <= (new Date()).getTime()) {
            tokenStr = this.updAccessToken(wecId);
        }
        return tokenStr;
    } else {
        return null;
    }
}


public void updWeiEnter(LzWeiEnter weiEnter){
    // TODO Auto-generated method stub
    hibernateDao.update(weiEnter);
}


public String getCurtAccTokenStrByApp(String appId){
    LzWeiEnter enter = this.getWeiEnterByAppId(appId);
    Integer wecId = enter.getWecId();
    return this.getCurrentAccessTokenStr(wecId);
}


public Page queryWatcher(LzWeiWatcher watcher){
    String wacWatchStatus_Q = watcher.getWacWatchStatus_Q();
    String wacBindStatus_Q = watcher.getWacBindStatus_Q();
    String wacMobile_Q = watcher.getWacMobile_Q();
    String wacUserName_Q = watcher.getWacUserName_Q();
    String wacContract_Q = watcher.getWacContract_Q();
    String wacEnterPrise_Q = watcher.getWacEnterPrise_Q();
    String wacNickName_Q = watcher.getWacNickName_Q();
    String wacStatus_Q = watcher.getWacStatus_Q();
    String wacWecId_Q = watcher.getWacWecId_Q();
    StringBuilder sql = new StringBuilder(" 	select " + "	a.WAC_ID," + "	a.WAC_OPENID," + "	a.WAC_SUBSCRIBE," + "	a.WAC_NICK_NAME," + "	a.WAC_SEX," + "	a.WAC_LANGUAGE," + "	a.WAC_CITY," + "	a.WAC_PROVINCE," + "	a.WAC_COUNTRY," + "	a.WAC_HEAD_IMG_URL," + "	a.WAC_SUBSCRIBE_TIME," + "	a.WAC_STATUS ," + "	a.WAC_REGISTOR_DATE");
    StringBuilder sqlCnt = new StringBuilder(" 	select count(*) ");
    StringBuilder sqlConf = new StringBuilder("	from LZ_WEI_WATCHER a " + "	where 1=1 ");
    List<Object> paraList = new ArrayList<Object>();
    if (wacWatchStatus_Q != null && wacWatchStatus_Q.length() > 0) {
        sqlConf.append(" and a.WAC_SUBSCRIBE = ? ");
        paraList.add(wacWatchStatus_Q);
    }
    if (wacNickName_Q != null && wacNickName_Q.length() > 0) {
        sqlConf.append(" and a.WAC_NICK_NAME like ? ");
        paraList.add("%" + wacNickName_Q + "%");
    }
    if (wacStatus_Q != null && wacStatus_Q.length() > 0) {
        sqlConf.append(" and a.WAC_STATUS = ? ");
        paraList.add(wacStatus_Q);
    }
    if (wacWecId_Q != null && wacWecId_Q.length() > 0) {
        sqlConf.append(" and a.WAC_WEC_ID = ? ");
        paraList.add(wacWecId_Q);
    }
    sqlCnt.append(sqlConf);
    sql.append(sqlConf);
    sql.append(" order by a.WAC_SUBSCRIBE_TIME desc ");
    // System.out.println(sqlCnt.toString());
    Page page = new Page(sql.toString(), sqlCnt.toString(), watcher.getCurrentPage(), watcher.getPageSize(), paraList.toArray());
    jdbcDao.queryForPage(page);
    StringBuilder sqlTotalWatcher = new StringBuilder(" select count(*) from LZ_WEI_WATCHER ");
    if (wacWecId_Q != null && wacWecId_Q.length() > 0) {
        sqlTotalWatcher.append(" where WAC_WEC_ID = " + wacWecId_Q);
    }
    watcher.setSumTotal(jdbcDao.queryForString(sqlTotalWatcher.toString()));
    return page;
}


public List<Map<String,Object>> queryWatcherLocalList(Integer wecId){
    String sql = " select a.WAC_OPENID from LZ_WEI_WATCHER a where a.WAC_WEC_ID = ? ";
    return jdbcDao.queryForList(sql, new Object[] { wecId });
}


@Transactional(readOnly = true)
public Page queryAccessToken(LzWeiAccesstoken token){
    // TODO Auto-generated method stub
    String wecid_Q = token.getWatWecId_Q();
    StringBuilder sql = new StringBuilder("select " + " a.WAT_ID," + " a.WAT_TOKEN," + " date_format(a.WAT_EXPIRES_IN,'%Y-%m-%d')  as WAT_EXPIRES_IN," + " date_format(a.WAT_CREAT_TIME,'%Y-%m-%d')  as WAT_CREAT_TIME," + " a.WAT_WEC_ID," + " a.WAT_APPID," + " b.WEC_APP_NAME," + " a.WAT_STATUS " + " from LZ_WEI_ACCESSTOKEN a left join LZ_WEI_ENTER b on a.WAT_WEC_ID = b.WEC_ID where 1=1 ");
    StringBuilder sqlCnt = new StringBuilder(" select count(*) " + " from LZ_WEI_ACCESSTOKEN a left join LZ_WEI_ENTER b on a.WAT_WEC_ID = b.WEC_ID where 1=1 ");
    List<Object> paraList = new ArrayList<Object>();
    if (wecid_Q != null && wecid_Q.length() > 0) {
        sql.append(" and b.WEC_ID = ? ");
        sqlCnt.append(" and b.WEC_ID = ? ");
        paraList.add(wecid_Q);
    }
    Page page = new Page(sql.toString(), sqlCnt.toString(), token.getCurrentPage(), token.getPageSize(), paraList.toArray());
    jdbcDao.queryForPage(page);
    return page;
}


public LzWeiJsapiTicket getCurrentTikcet(Integer wecId){
    String hql = "from LzWeiJsapiTicket a where a.wjtWecId = ? and a.wjtStatus  = 1 ";
    List<LzWeiJsapiTicket> ticketList = hibernateDao.query(hql, new Object[] { wecId });
    if (ticketList.size() > 0) {
        LzWeiJsapiTicket ticket = ticketList.get(0);
        Date expireIn = DateUtil.parseDate(ticket.getWjtExpiresIn(), "yyyy-MM-dd HH:mm:ss");
        if (expireIn.getTime() <= (new Date()).getTime()) {
            this.updJsApiTicket(wecId);
            List<LzWeiJsapiTicket> ticketList1 = hibernateDao.query(hql, new Object[] { wecId });
            ticket = ticketList1.get(0);
        }
        return ticket;
    } else {
        this.updJsApiTicket(wecId);
        List<LzWeiJsapiTicket> ticketList1 = hibernateDao.query(hql, new Object[] { wecId });
        return ticketList1.get(0);
    }
}


public void addAccessToken(String accessToken,int expires_in,Integer wecId,String appid){
    LzWeiAccesstoken accToken = new LzWeiAccesstoken();
    Date now = new Date();
    now.setTime(now.getTime() + (expires_in * 1000));
    accToken.setWatToken(accessToken);
    accToken.setWatExpiresIn(DateUtil.parseString(now, "yyyy-MM-dd HH:mm:ss"));
    accToken.setWatWecId(wecId);
    accToken.setWatAppid(appid);
    accToken.setWatStatus("1");
    hibernateDao.add(accToken);
}


public void disabledJsApiTicket(Integer wecId){
    String sql = "update LZ_WEI_JSAPI_TICKET set WJT_STATUS  = '0' where WJT_WEC_ID = ? ";
    jdbcDao.update(sql, new Object[] { wecId });
}


public void disabledAccessToken(Integer wecId){
    String sql = "update LZ_WEI_ACCESSTOKEN set WAT_STATUS = '0' where WAT_WEC_ID = ? ";
    jdbcDao.update(sql, new Object[] { wecId });
}


public LzWeiJsapiTicket getCurrentJsapiTicket(Integer wecId){
    LzWeiEnter enter = this.getWeiEnterById(wecId);
    String hql = "from LzWeiJsapiTicket a where a.wjtWecId = ? and a.wjtStatus  = 1 ";
    List<LzWeiJsapiTicket> ticketList = hibernateDao.query(hql, new Object[] { wecId });
    if (ticketList.size() > 0) {
        LzWeiJsapiTicket ticket = ticketList.get(0);
        String ticketStr = ticket.getWjtJsapiTicket();
        Date expireIn = DateUtil.parseDate(ticket.getWjtExpiresIn(), "yyyy-MM-dd HH:mm:ss");
        if (expireIn.getTime() <= (new Date()).getTime()) {
            ticketStr = this.updJsApiTicket(wecId);
            List<LzWeiJsapiTicket> ticketList1 = hibernateDao.query(hql, new Object[] { wecId });
            ticket = ticketList1.get(0);
        }
        return ticket;
    } else {
        return null;
    }
}


public LzWeiWatcher getWatcherByOpenId(String openidStr,Integer wecId){
    String sql = " select  " + " WAC_ID," + " WAC_OPENID," + " WAC_SUBSCRIBE," + " WAC_NICK_NAME," + " WAC_SEX," + " WAC_LANGUAGE,WAC_CITY,WAC_PROVINCE,WAC_COUNTRY,WAC_HEAD_IMG_URL," + " WAC_SUBSCRIBE_TIME,WAC_STATUS,WAC_WEC_ID," + " WAC_APPID " + " from LZ_WEI_WATCHER where WAC_OPENID = ? and WAC_WEC_ID = ? LIMIT 1 ";
    List<Map<String, Object>> list = jdbcDao.queryForList(sql, new Object[] { openidStr, wecId });
    if (list.size() > 0) {
        Map<String, Object> map = list.get(0);
        LzWeiWatcher watcher = new LzWeiWatcher();
        watcher.setWacId((Integer) map.get("WAC_ID"));
        watcher.setWacOpenid((String) map.get("WAC_OPENID"));
        watcher.setWacSubscribe((Integer) map.get("WAC_SUBSCRIBE"));
        watcher.setWacNickName((String) map.get("WAC_NICK_NAME"));
        watcher.setWacSex((String) map.get("WAC_SEX"));
        watcher.setWacLanguage((String) map.get("WAC_LANGUAGE"));
        watcher.setWacCity((String) map.get("WAC_CITY"));
        watcher.setWacProvince((String) map.get("WAC_PROVINCE"));
        watcher.setWacCountry((String) map.get("WAC_COUNTRY"));
        watcher.setWacHeadImgUrl((String) map.get("WAC_HEAD_IMG_URL"));
        watcher.setWacSubscribeTime(map.get("WAC_SUBSCRIBE_TIME") == null ? "" : DateUtil.parseString((Date) map.get("WAC_SUBSCRIBE_TIME"), "yyyy-MM-dd HH:mm:ss"));
        watcher.setWacStatus((String) map.get("WAC_STATUS"));
        watcher.setWacAppid((String) map.get("WAC_APP_ID"));
        watcher.setWacWecId((Integer) map.get("WAC_WEC_ID"));
        return watcher;
    } else {
        return null;
    }
}


public void addKeyWordsByEnter(LzWeiEnter weiEnter,String[] keyWrds,String[] keyWrdMsgIds,WcAdmin admin){
    // TODO Auto-generated method stub
    String sql0 = "delete from LZ_WEI_KEYWORD_MESSAGE where WKG_WEC_ID = ?";
    jdbcDao.delete(sql0, new Object[] { weiEnter.getWecId() });
    String sql = " insert into LZ_WEI_KEYWORD_MESSAGE " + " (" + "	WKG_WEC_ID," + "	WKG_APP_ID," + "	WKG_KEYWORDS," + "	WKG_WMG_ID," + "	WKG_STATUS," + "	WKG_DESC," + "	WKG_REGISTOR," + "	WKG_REGISTDATE" + "	) values (?,?,?,?,?,?,?,?)";
    List<Object[]> batchArgs = new ArrayList<Object[]>();
    if (keyWrdMsgIds != null && keyWrdMsgIds.length > 0) {
        for (int i = 0; i < keyWrdMsgIds.length; i++) {
            String msgId = keyWrdMsgIds[i];
            String[] keyWrd = keyWrds[i].split(",");
            for (int j = 0; j < keyWrd.length; j++) {
                String kwd = keyWrd[j];
                if (kwd != null && kwd.length() > 0) {
                    Object[] obj = new Object[] { weiEnter.getWecId(), weiEnter.getWecAppId(), kwd, msgId, "1000", "", admin.getWadId(), DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss") };
                    batchArgs.add(obj);
                }
            }
        }
        jdbcDao.batchUpdate(sql, batchArgs);
    }
}


}