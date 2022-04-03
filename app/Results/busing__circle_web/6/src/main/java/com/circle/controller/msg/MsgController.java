package com.circle.controller.msg;
 import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.circle.pojo.user.User;
import com.circle.service.msg.IMsgService;
import com.xwtec.xwserver.constant.ConstantBusiKeys;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.util.CommonUtil;
import com.DTO.User;
@Controller
@RequestMapping("msg")
public class MsgController {

 private  Logger logger;

@Resource
 private IMsgService msgService;


@ResponseBody
@RequestMapping("delmsg.action")
public boolean delMSg(String msgId){
    try {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", msgId);
        paramMap.put("status", "-1");
        return msgService.updateMsgStatus(paramMap);
    } catch (SPTException e) {
        return false;
    }
}


@ResponseBody
@RequestMapping("getmsgcount.action")
public int getMSgCount(HttpServletRequest request){
    try {
        User user = (User) request.getSession().getAttribute(ConstantBusiKeys.SessionKey.SYS_USER);
        if (user == null) {
            return 0;
        }
        int cnt = msgService.queryMsgCount(user.getId() + "");
        return cnt;
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        return 0;
    }
}


@ResponseBody
@RequestMapping("secondbuttonclick.action")
public boolean secondButtonClick(String msgId){
    try {
        return msgService.secondClickCall(msgId);
    } catch (SPTException e) {
        return false;
    }
}


@ResponseBody
@RequestMapping("firstbuttonclick.action")
public boolean firstButtonClick(String msgId){
    try {
        return msgService.firstClickCall(msgId);
    } catch (SPTException e) {
        return false;
    }
}


}