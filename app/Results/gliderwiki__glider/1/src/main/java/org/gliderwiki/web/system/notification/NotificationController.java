package org.gliderwiki.web.system.notification;
 import javax.annotation.Resource;
import org.gliderwiki.web.space.controller.SpaceController;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.system.notification.service.NotifiationService;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.gliderwiki.Interface.NotifiationService;
import org.gliderwiki.DTO.MemberSessionVo;
@Controller
@RequestMapping("/noti")
public class NotificationController {

 private Logger logger;

@Resource(name = "notifiationService")
 private  NotifiationService notifiationService;

@Resource(name = "commonService")
 private  CommonService commonService;


@RequestMapping(value = "/view/check", method = RequestMethod.GET)
@ResponseBody
public String realTimeViewCheck(MemberSessionVo loginUser){
    logger.debug("realTimeViewCheck : {}", commonService.realNotiView(loginUser.getWeUserIdx()));
    return commonService.realNotiView(loginUser.getWeUserIdx());
}


@RequestMapping(value = "/newNotiCount", method = RequestMethod.GET)
@ResponseBody
public int getNewNotiCount(MemberSessionVo loginUser){
    return notifiationService.getNewNotiCount(loginUser.getWeUserIdx());
}


@RequestMapping(value = "/view/update", method = RequestMethod.POST)
@ResponseBody
public void realTimeViewUpadte(MemberSessionVo loginUser,String isChecked){
    logger.debug("isChecked : {}" + isChecked);
    commonService.changeRealTimeView(loginUser.getWeUserIdx(), isChecked);
}


}