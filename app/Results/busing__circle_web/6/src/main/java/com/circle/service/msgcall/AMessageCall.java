package com.circle.service.msgcall;
 import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import com.circle.pojo.msg.MessageBean;
import com.circle.service.msg.IMsgService;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.util.SpringUtils;
public class AMessageCall {


public boolean secondClickCall(MessageBean messageBean){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("id", messageBean.getId());
    paramMap.put("status", "-1");
    IMsgService msgService = SpringUtils.getBean("msgService");
    return msgService.updateMsgStatus(paramMap);
}


public boolean firstClickCall(MessageBean messageBean){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("id", messageBean.getId());
    paramMap.put("status", "1");
    IMsgService msgService = SpringUtils.getBean("msgService");
    return msgService.updateMsgStatus(paramMap);
}


}