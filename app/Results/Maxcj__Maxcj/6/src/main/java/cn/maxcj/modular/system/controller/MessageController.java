package cn.maxcj.modular.system.controller;
 import cn.maxcj.core.common.annotion.BussinessLog;
import cn.maxcj.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.maxcj.modular.system.model.Message;
import cn.maxcj.modular.system.service.IMessageService;
import java.util.Date;
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController{

 private  String PREFIX;

@Autowired
 private  IMessageService messageService;


@BussinessLog(value = "新增留言", key = "message")
@RequestMapping(value = "/add")
@ResponseBody
public Object add(Message message){
    message.setUsername(ShiroKit.getUser().getName());
    message.setCreatetime(new Date());
    String strXml = message.getContent().replace("&amp; lt;", "<" + "");
    String strXml1 = strXml.replace("&amp; gt;", ">" + "");
    message.setContent(strXml1);
    messageService.insert(message);
    return SUCCESS_TIP;
}


@RequestMapping("/message_add")
public String messageAdd(){
    return PREFIX + "message_add.html";
}


@RequestMapping("")
public String index(){
    return PREFIX + "message.html";
}


@BussinessLog(value = "修改留言", key = "message")
@RequestMapping(value = "/update")
@ResponseBody
public Object update(Message message){
    String strXml = message.getContent().replace("&amp; lt;", "<" + "");
    String strXml1 = strXml.replace("&amp; gt;", ">" + "");
    message.setContent(strXml1);
    messageService.updateById(message);
    return SUCCESS_TIP;
}


@RequestMapping("/message_update/{messageId}")
public String messageUpdate(Integer messageId,Model model){
    Message message = messageService.selectById(messageId);
    model.addAttribute("item", message);
    LogObjectHolder.me().set(message);
    return PREFIX + "message_edit.html";
}


@RequestMapping(value = "/detail/{messageId}")
@ResponseBody
public Object detail(Integer messageId){
    return messageService.selectById(messageId);
}


@RequestMapping("/message")
public String message(){
    return PREFIX + "message_list.html";
}


@RequestMapping(value = "/list")
@ResponseBody
public Object list(String condition){
    return messageService.list(condition);
}


@BussinessLog(value = "删除留言", key = "messageId")
@RequestMapping(value = "/delete")
@ResponseBody
public Object delete(Integer messageId){
    messageService.deleteById(messageId);
    return SUCCESS_TIP;
}


}