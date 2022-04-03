package com.circle.service.msg;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.msg.MessageBean;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
public interface IMsgService {


public boolean secondClickCall(String msgId)
;

public boolean firstClickCall(String msgId)
;

public boolean updateMsgStatus(Map<String,?> paramMap)
;

public List<Map<String,Object>> getUserMessage(Page page,int id)
;

public int queryCircleMsgCount(String sendUserId,String reciveUserId)
;

public int queryMsgCount(String userId)
;

public void batchAddMsgInfo(List<MessageBean> msgList)
;

}