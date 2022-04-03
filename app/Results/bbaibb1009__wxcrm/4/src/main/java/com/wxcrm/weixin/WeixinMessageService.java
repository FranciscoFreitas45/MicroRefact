package com.wxcrm.weixin;
 import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.oilchem.weixin.Constant;
import com.oilchem.weixin.message.response.LzWeiBaseMsgResp;
import com.oilchem.weixin.message.response.LzWeiTextMsgResp;
import com.wxcrm.common.dao.IHibernateDao;
import com.wxcrm.common.dao.IJdbcDao;
import com.wxcrm.member.IWeiMemberService;
import com.wxcrm.member.LzWeiMember;
import com.wxcrm.util.Page;
import com.wxcrm.weixin.pojo.LzWeiArticleMessage;
import com.wxcrm.weixin.pojo.WeixinUser;
import com.wxcrm.Interface.IJdbcDao;
import com.wxcrm.Interface.IHibernateDao;
import com.wxcrm.Interface.IWeiMemberService;
@Service
@Transactional
public class WeixinMessageService implements IWeixinMessageService{

@Autowired
 private  IJdbcDao jdbcDao;

@Autowired
 private  IHibernateDao hibernateDao;

@Autowired
 private  IWeixinService weixinservice;

@Autowired
 private  IWeiMemberService weimemberservice;

@Autowired
 private  IWeixinUserService weixinUserService;

 private  Logger log;


public LzWeiBaseMsgResp queryDefaultMsgByAppId(LzWeiBaseMsgResp respMessage,String content,String appId){
    log.info("��ȡ�ظ��ı���Ϣ");
    LzWeiMessage msg = this.getKeyWordMsgByContent(content, appId);
    // ���ж�content�ǲ��ǹؼ���
    if (msg != null) {
        String msgType = msg.getWmgMsgType();
        if (msgType.equals("2")) {
            LzWeiTextMsgResp msgResp = new LzWeiTextMsgResp(respMessage);
            msgResp.setContent(msg.getWmgContent());
            return msgResp;
        } else {
            log.error("***********************318�У��ǹؼ��� �������ı���Ϣ***********************");
            return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
        }
    } else // ���ǹؼ��ֵľͷ���Ĭ�ϻظ���Ϣ
    {
        String sql = " select b.WMG_ID, b.WMG_MSG_TYPE,b.WMG_CONTENT " + " from LZ_WEI_ENTER a " + " join LZ_WEI_MESSAGE b on a.WEC_DEFAULT_MSG = b.WMG_ID  " + " where a.WEC_APP_ID = ? ";
        List<Map<String, Object>> list = jdbcDao.queryForList(sql, new Object[] { appId });
        if (list.size() > 0) {
            Map<String, Object> map = list.get(0);
            Integer wmgId = (Integer) map.get("WMG_ID");
            msg = this.getLzWeiMessageById(wmgId);
            String msgType = msg.getWmgMsgType();
            if (msgType.equals("2")) {
                log.error("***********************338��***********************");
                LzWeiTextMsgResp msgResp = new LzWeiTextMsgResp(respMessage);
                msgResp.setContent(msg.getWmgContent());
                return msgResp;
            } else {
                log.error("***********************345�У����Ƿ����ı���Ϣ***********************");
                return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
            }
        } else {
            log.error("***********************351�У��鲻������***********************");
            log.error(" select b.WMG_ID, b.WMG_MSG_TYPE,b.WMG_CONTENT " + " from LZ_WEI_ENTER a " + " join LZ_WEI_MESSAGE b on a.WEC_DEFAULT_MSG = b.WMG_ID  " + " where a.WEC_APP_ID = " + appId);
            return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
        }
    }
}


public String processRequest_Jar(HttpServletRequest request,String token,String encodingAESKey,String appId){
    String respMessage = null;
    String encrypt_type = (String) request.getParameter("encrypt_type") == null ? "" : (String) request.getParameter("encrypt_type");
    String msg_signature = (String) request.getParameter("msg_signature") == null ? "" : (String) request.getParameter("msg_signature");
    String timestamp = (String) request.getParameter("timestamp") == null ? "" : (String) request.getParameter("timestamp");
    String nonce = (String) request.getParameter("nonce") == null ? "" : (String) request.getParameter("nonce");
    // Ĭ�Ϸ��ص��ı���Ϣ����
    String respContent = "�������쳣�����Ժ��ԣ�";
    InputStream inputStream = request.getInputStream();
    // xml�������
    Map<String, String> requestMap = new HashMap<String, String>();
    // ���ռ��ܷ�ʽ�Ĳ�ͬ������Ϣ��Ԥ����
    if (encrypt_type.equals("aes")) {
        log.error("��ǰ�ǰ�ȫģʽ!");
        requestMap = com.oilchem.weixin.message.MessageUtil.parseXmlAes(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);
        log.error(requestMap);
    } else {
        log.error("��ǰ�Ǽ���ģʽ������ģʽ!");
        requestMap = com.oilchem.weixin.message.MessageUtil.parseXmlRaw(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);
        log.error(requestMap);
    }
    // ��ͬ����Ϣ���ݷ��ز�ͬ����Ӧ
    LzWeiBaseMsgResp respMsg = fenleiReq_Jar(requestMap, appId);
    // �ٸ��ݼ��ܷ�ʽ�Ĳ�ͬ������Ϣ����Ӧǰ��װ
    respMessage = com.oilchem.weixin.message.MessageUtil.baseMessageToXml(respMsg, encrypt_type, token, encodingAESKey, appId, msg_signature, timestamp, nonce);
    return respMessage;
}


public Page queryLzWeiMsg(LzWeiMessage msg){
    // TODO Auto-generated method stub
    String wmgReplyType_Q = msg.getWmgReplyType_Q();
    String wmgMsgType_Q = msg.getWmgMsgType_Q();
    String wmgAesType_Q = msg.getWmgAesType_Q();
    StringBuilder sql = new StringBuilder(" select " + " a.WMG_ID," + " a.WMG_CONTENT," + " a.WMG_REPLY_TYPE," + " a.WMG_MSG_TYPE," + " a.WMG_AES_TYPE," + " a.WMG_STATUS," + " a.WMG_DESC," + " a.WMG_REGISTOR," + " a.WMG_REGISTDATE " + " from LZ_WEI_MESSAGE a " + " where 1=1 ");
    StringBuilder sqlCnt = new StringBuilder(" select count(*) from LZ_WEI_MESSAGE a " + " where 1=1 ");
    List<Object> paraList = new ArrayList<Object>() {
    };
    if (wmgReplyType_Q != null && wmgReplyType_Q.length() > 0) {
        sql.append(" and a.WMG_REPLY_TYPE =  ? ");
        sqlCnt.append(" and a.WMG_REPLY_TYPE = ? ");
        paraList.add(wmgReplyType_Q);
    }
    if (wmgMsgType_Q != null && wmgMsgType_Q.length() > 0) {
        sql.append(" and a.WMG_MSG_TYPE =  ? ");
        sqlCnt.append(" and a.WMG_MSG_TYPE = ? ");
        paraList.add(wmgMsgType_Q);
    }
    if (wmgAesType_Q != null && wmgAesType_Q.length() > 0) {
        sql.append(" and a.WMG_AES_TYPE =  ? ");
        sqlCnt.append(" and a.WMG_AES_TYPE = ? ");
        paraList.add(wmgAesType_Q);
    }
    Page page = new Page(sql.toString(), sqlCnt.toString(), msg.getCurrentPage(), msg.getPageSize(), paraList.toArray());
    jdbcDao.queryForPage(page);
    return page;
}


public void main(String[] args){
    Map<String, String> map = new HashMap<String, String>();
    map.put("fromUser", "��«��");
    map.put("toUser", "��«��");
    System.out.println(map);
}


public void addLzWeiMsg(LzWeiMessage msg){
    // TODO Auto-generated method stub
    hibernateDao.add(msg);
}


public LzWeiBaseMsgResp querySubscribeMsgByAppId(LzWeiBaseMsgResp respMessage,String appId){
    LzWeiMessage msg = this.getSubScribeMsgByApp(appId);
    // ���ж�content�ǲ��ǹؼ���
    if (msg != null) {
        String msgType = msg.getWmgMsgType();
        if (msgType.equals("2")) {
            LzWeiTextMsgResp msgResp = new LzWeiTextMsgResp(respMessage);
            msgResp.setContent(msg.getWmgContent());
            return msgResp;
        } else {
            log.error("***********************318�У��й�ע�ظ� ������������Ϣ***********************");
            return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
        }
    } else {
        log.error("***********************387�У�û�����ù�ע�ظ�***********************");
        return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
    }
}


public LzWeiMessage getKeyWordMsgByContent(String content,String appId){
    String sql = " select a.WKG_WMG_ID from LZ_WEI_KEYWORD_MESSAGE a " + " join LZ_WEI_ENTER b on a.WKG_WEC_ID = b.WEC_ID  " + " where b.WEC_APP_ID = ? and a.WKG_KEYWORDS = ? ";
    List<Map<String, Object>> list = jdbcDao.queryForList(sql, new Object[] { appId, content });
    if (list.size() > 0) {
        Map<String, Object> map = list.get(0);
        Integer wmgId = (Integer) map.get("WKG_WMG_ID");
        LzWeiMessage msgWei = this.getLzWeiMessageById(wmgId);
        return msgWei;
    } else {
        return null;
    }
}


public List<Map<String,Object>> queryKeywordListByWei(Integer wecId){
    // TODO Auto-generated method stub
    String sql = " select distinct  " + "  LEFT(a.kwd,LENGTH(a.kwd)-1) as kwd,b.WMG_CONTENT,b.WMG_ID ,a.WKG_WEC_ID,c.WKG_APP_ID " + "  from  " + "  (  " + " 		select bb.WMG_ID,cc.WKG_WEC_ID, " + "		(select group_concat(WKG_KEYWORDS) from LZ_WEI_KEYWORD_MESSAGE where WKG_WMG_ID = bb.WMG_ID ) as kwd from LZ_WEI_MESSAGE bb left join LZ_WEI_KEYWORD_MESSAGE cc on bb.WMG_ID = cc.WKG_WMG_ID group by bb.WMG_ID ,cc.WKG_WEC_ID " + " ) a  " + " left join LZ_WEI_MESSAGE b on a.WMG_ID = b.WMG_ID  " + " join LZ_WEI_KEYWORD_MESSAGE c on c.WKG_WEC_ID = a.WKG_WEC_ID  " + " where c.WKG_WEC_ID = ? ";
    return jdbcDao.queryForList(sql, new Object[] { wecId });
}


public void updLzWeiMsg(LzWeiMessage msg){
    // TODO Auto-generated method stub
    hibernateDao.update(msg);
}


public void addLzWeiArticleMsg(LzWeiArticleMessage article){
    // TODO Auto-generated method stub
    hibernateDao.add(article);
}


public LzWeiMessage getLzWeiMessageById(Integer id){
    return hibernateDao.get(LzWeiMessage.class, id);
}


public LzWeiMessage getSubScribeMsgByApp(String appId){
    String sql = " select a.WEC_SUBSCRIBE_MSG from LZ_WEI_ENTER a " + " where a.WEC_APP_ID = ?  ";
    List<Map<String, Object>> list = jdbcDao.queryForList(sql, new Object[] { appId });
    if (list.size() > 0) {
        Map<String, Object> map = list.get(0);
        Integer wmgId = (Integer) map.get("WEC_SUBSCRIBE_MSG");
        LzWeiMessage msgWei = this.getLzWeiMessageById(wmgId);
        return msgWei;
    } else {
        return null;
    }
}


public LzWeiBaseMsgResp querySubscribeAgainMsgByAppId(LzWeiBaseMsgResp respMessage){
    LzWeiTextMsgResp msgResp = new LzWeiTextMsgResp(respMessage);
    msgResp.setContent("�����ѣ���ӭ����!");
    return msgResp;
}


public LzWeiBaseMsgResp fenleiReq_Jar(Map<String,String> requestMap,String appId){
    // ���ͷ��ʺ�(open_id)
    String fromUserName = requestMap.get("FromUserName") == null ? "" : requestMap.get("FromUserName");
    // �����ʺ�  (app_Id)
    String toUserName = requestMap.get("ToUserName") == null ? "" : requestMap.get("ToUserName");
    // ��Ϣ����
    String msgType = requestMap.get("MsgType") == null ? "" : requestMap.get("MsgType");
    // ����������Ϣ
    String content = requestMap.get("Content") == null ? "" : requestMap.get("Content");
    String respContent = "";
    LzWeiBaseMsgResp respMessage = new LzWeiBaseMsgResp();
    respMessage.setToUserName(fromUserName);
    respMessage.setFromUserName(toUserName);
    respMessage.setCreateTime(new Date().getTime());
    respMessage.setFuncFlag(0);
    log.error("���ڵ���Ϣ������:" + msgType);
    // �ı���Ϣ
    if (msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) {
        log.error("�������ı���Ϣ1");
        // �����ｫ���͹�������Ϣ���з��ദ��
        // �ȸ���appid ȡ��Ĭ�ϻظ���Ϣ
        respMessage = this.queryDefaultMsgByAppId(respMessage, content, appId);
        respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_TEXT);
    } else // ͼƬ��Ϣ
    if (msgType.equals(Constant.REQ_MESSAGE_TYPE_IMAGE)) {
        log.error("������ͼƬ��Ϣ");
        respContent = "�����͵���ͼƬ��Ϣ��";
        respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_IMAGE);
    } else // ����λ����Ϣ
    if (msgType.equals(Constant.REQ_MESSAGE_TYPE_LOCATION)) {
        log.error("�����ǵ���λ����Ϣ");
        respContent = "�����͵��ǵ���λ����Ϣ��";
        respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_LOCATION);
    } else // ������Ϣ
    if (msgType.equals(Constant.REQ_MESSAGE_TYPE_LINK)) {
        respContent = "�����͵���������Ϣ��";
        respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_LINK);
    } else // ��Ƶ��Ϣ
    if (msgType.equals(Constant.REQ_MESSAGE_TYPE_VOICE)) {
        respContent = "�����͵�����Ƶ��Ϣ��";
        respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_VOICE);
    } else // �¼�����
    if (msgType.equals(Constant.REQ_MESSAGE_TYPE_EVENT)) {
        // �¼�����
        String eventType = requestMap.get("Event");
        // ����
        if (eventType.equals(Constant.EVENT_TYPE_SUBSCRIBE)) {
            // ���һ����Ա��¼ ֻ�ǹ�ע��
            LzWeiEnter enter = weixinservice.getWeiEnterByAppId(appId);
            // �ȼ�鵱ǰopenid�ڸ�appid����û�а�
            if (weimemberservice.checkOpenIdExsit(fromUserName, enter.getWecId())) {
                // �ظ������ѣ���ӭ����
                respMessage = this.querySubscribeAgainMsgByAppId(respMessage);
            } else {
                WeixinUser user = weixinUserService.getUserInfo(fromUserName, appId);
                LzWeiMember member = new LzWeiMember(fromUserName, enter.getWecId(), enter.getWecEnterId(), user);
                weimemberservice.addWeiMember(member);
                // ��η���һ����Ϣ
                respMessage = this.querySubscribeMsgByAppId(respMessage, appId);
            }
            log.error("5");
        } else // ȡ������
        if (eventType.equals(Constant.EVENT_TYPE_UNSUBSCRIBE)) {
            // TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
            // ȡ����ע��Ҫ���û���״̬��Ϊ��ȡ����ע
            this.updWatcherByUnSubscribe(respMessage, appId);
            log.error("ȡ������");
            respMessage.setMsgType(Constant.EVENT_TYPE_UNSUBSCRIBE);
        } else // �Զ���˵�����¼�
        if (eventType.equals(Constant.EVENT_TYPE_CLICK)) {
            // TODO �Զ���˵�Ȩû�п��ţ��ݲ����������Ϣ
            respMessage.setMsgType(Constant.EVENT_TYPE_CLICK);
        }
    }
    return respMessage;
}


public Map<String,Object> getWeiArticleMap(Integer id){
    // TODO Auto-generated method stub
    String sql = new String(" select * from lz_wei_article_message a where a.wam_id = ? ");
    return jdbcDao.queryForMap(sql, new Object[] { id });
}


public void updWatcherByUnSubscribe(LzWeiBaseMsgResp respMessage,String appId){
    // ����openId��appid�ҵ���Ӧ��watcher
    // ��Ϊȡ����ע
    LzWeiEnter wec = weixinservice.getWeiEnterByAppId(appId);
    String openId = respMessage.getToUserName();
    LzWeiWatcher watcher = weixinservice.getWatcherByOpenId(openId, wec.getWecId());
    watcher.setWacSubscribe(0);
    weixinservice.updWatcher(watcher);
}


}