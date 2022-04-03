package com.gbcom.system.controller;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gbcom.system.daoservice.SysMessageService;
import com.gbcom.system.daoservice.SysUserService;
import com.gbcom.system.domain.SysMessage;
import com.gbcom.system.domain.SysPerson;
import com.gbcom.system.domain.SysUser;
import com.gbcom.system.manager.SysMessageManager;
import com.gbcom.system.utils.DateUtil;
import com.hc.core.controller.BaseCRUDActionController;
import com.gbcom.Interface.SysUserService;
@Controller
public class SysMessageController extends BaseCRUDActionController{

@Autowired
 private SysMessageService sysMessageService;

@Autowired
 private SysMessageManager sysMessageManager;

@Autowired
 private SysUserService sysUserService;


@RequestMapping
public String view(HttpServletResponse response,Long id,Model model){
    SysMessage message = sysMessageService.get(id);
    String createName = "";
    if (message.getCreatorId() != null) {
        SysUser user = sysUserService.get(message.getCreatorId());
        if (user != null) {
            createName = user.getLoginName();
        }
    }
    model.addAttribute("msg", message);
    model.addAttribute("createName", createName);
    return "view/system/sysMessage/view";
}


@RequestMapping
public String emailBox(HttpServletResponse response,Model model){
    Integer type = 3;
    List<SysMessage> list = sysMessageManager.getMessages(type, 15);
    model.addAttribute("msgs", list);
    model.addAttribute("num", sysMessageManager.getUnreadNum());
    return "view/system/sysMessage/emailBox";
}


@RequestMapping
public String emailView(HttpServletResponse response,Long id,Model model){
    SysMessage message = sysMessageService.get(id);
    String createName = "";
    if (message.getCreatorId() != null) {
        SysUser user = sysUserService.get(message.getCreatorId());
        if (user != null) {
            createName = user.getLoginName();
        }
    }
    model.addAttribute("msg", message);
    model.addAttribute("createName", createName);
    return "view/system/sysMessage/emailView";
}


@RequestMapping
public String getLastMsg(HttpServletResponse response,Integer type,Model model){
    List<SysMessage> list = sysMessageManager.getMessages(type);
    List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
    for (int i = 0; i < list.size(); i++) {
        SysMessage msg = list.get(i);
        Map<String, String> map = new HashedMap();
        map.put("title", msg.getTitle());
        map.put("countent", msg.getContent());
        map.put("data", DateUtil.format(msg.getCreateTime(), "yyyy/MM/dd HH:mm:ss"));
        map.put("id", msg.getId().toString());
        if (msg.getCreatorId() != null) {
            SysUser user = sysUserService.get(msg.getCreatorId());
            if (user != null) {
                map.put("name", user.getLoginName());
                map.put("userId", user.getId().toString());
                SysPerson person = user.getPerson();
                if (person != null) {
                    if (person.getAvatarUrl() != null && !"".equals(person.getProfilePhotoUrl())) {
                        map.put("photo", person.getAvatarUrl());
                    } else {
                        map.put("photo", "../html/avatars/avatar.png");
                    }
                }
            }
        }
        paramList.add(map);
    }
    model.addAttribute("msgs", paramList);
    model.addAttribute("num", sysMessageManager.getUnreadNum());
    return "view/system/sysMessage/emailMsgList";
}


@RequestMapping
public String messageBox(HttpServletResponse response,Model model,Integer type){
    List<SysMessage> list = sysMessageManager.getMessages(type, 15);
    model.addAttribute("msgs", list);
    model.addAttribute("num", sysMessageManager.getUnreadNum());
    String msgType = "";
    if (type == null) {
        msgType = "消息";
    } else {
        if (type == 1) {
            msgType = "通知";
        } else if (type == 2) {
            msgType = "告警";
        }
    }
    model.addAttribute("type", msgType);
    return "view/system/sysMessage/messageBox";
}


@RequestMapping
public String getCountView(HttpServletResponse response,Model model){
    model.addAttribute("alarm", sysMessageManager.getUnreadNum(2));
    model.addAttribute("msg", sysMessageManager.getUnreadNum(1));
    return "view/system/sysMessage/countView";
}


}