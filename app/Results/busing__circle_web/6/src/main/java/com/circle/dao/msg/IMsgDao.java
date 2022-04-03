package com.circle.dao.msg;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.msg.MessageBean;
import com.circle.pojo.msg.MessageTypeBean;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
public interface IMsgDao {


public int updateMsgStatus(Map<String,?> paramMap)
;

public List<Map<String,Object>> getUserMessage(Page page,int id)
;

public int queryCircleMsgCount(String sendUserId,String reciveUserId)
;

public MessageBean getMessageInfoById(String id)
;

public int queryMsgCount(String userId)
;

public void batchAddMsgInfo(List<Map<String,?>> paramListMap)
;

public List<MessageTypeBean> queryMessageTypeBeans()
;

}