package com.circle.service.msg.impl;
 import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.circle.constant.SystemDict;
import com.circle.dao.msg.IMsgDao;
import com.circle.pojo.msg.MessageBean;
import com.circle.pojo.msg.MessageTypeBean;
import com.circle.service.msg.IMsgService;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
@Service("msgService")
public class MsgServiceImpl implements IMsgService{

 public  Logger logger;

@Resource
 private  IMsgDao msgDao;

 public  String FIRSTCLICKCALL;

 public  String SECONDCLICKCALL;


@Override
public boolean secondClickCall(String msgId){
    try {
        MessageBean messageBean = msgDao.getMessageInfoById(msgId);
        MessageTypeBean typeBean = SystemDict.MESSAGE_TYPE_MAP.get(messageBean.getTypeId());
        // 反射按钮对应的回调类
        Class<?> clazz = Class.forName(typeBean.getClasspath());
        Object obj = clazz.newInstance();
        Method rM = obj.getClass().getDeclaredMethod(SECONDCLICKCALL, new Class[] { MessageBean.class });
        // 调用规定的方法
        boolean flag = (Boolean) rM.invoke(obj, messageBean);
        return flag;
    } catch (Exception e) {
        logger.error(e.getMessage());
        return false;
    }
}


@Override
public boolean firstClickCall(String msgId){
    try {
        MessageBean messageBean = msgDao.getMessageInfoById(msgId);
        MessageTypeBean typeBean = SystemDict.MESSAGE_TYPE_MAP.get(messageBean.getTypeId());
        // 反射按钮对应的回调类
        Class<?> clazz = Class.forName(typeBean.getClasspath());
        Object obj = clazz.newInstance();
        Method rM = obj.getClass().getDeclaredMethod(FIRSTCLICKCALL, new Class[] { MessageBean.class });
        // 调用规定的方法
        boolean flag = (Boolean) rM.invoke(obj, messageBean);
        return flag;
    } catch (Exception e) {
        logger.error(e.getMessage());
        e.printStackTrace();
        return false;
    }
}


public boolean updateMsgStatus(Map<String,?> paramMap){
    int result = msgDao.updateMsgStatus(paramMap);
    return result > 0 ? true : false;
}


@Override
public List<Map<String,Object>> getUserMessage(Page page,int id){
    return msgDao.getUserMessage(page, id);
}


public int queryCircleMsgCount(String sendUserId,String reciveUserId){
    return msgDao.queryCircleMsgCount(sendUserId, reciveUserId);
}


public int queryMsgCount(String userId){
    return msgDao.queryMsgCount(userId);
}


public void batchAddMsgInfo(List<MessageBean> msgList){
    /**
     * 查询条件list
     */
    List<Map<String, ?>> paramList = new ArrayList<Map<String, ?>>();
    /**
     * 循环msgList给paramList赋值
     */
    for (MessageBean msg : msgList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 消息类型
        paramMap.put("typeId", msg.getTypeId());
        // 消息内容
        paramMap.put("msgCountent", msg.getMsgContent());
        // 发送人
        paramMap.put("sendUserid", msg.getSendUserId());
        // 接收人
        paramMap.put("reciveUserid", msg.getReciveUserId());
        // 消息参数
        paramMap.put("params", msg.getParams());
        /**
         * 添加到paramList
         */
        paramList.add(paramMap);
    }
    /**
     * 批量更新
     */
    msgDao.batchAddMsgInfo(paramList);
}


}