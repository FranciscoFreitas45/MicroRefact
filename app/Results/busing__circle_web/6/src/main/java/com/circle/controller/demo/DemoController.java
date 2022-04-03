package com.circle.controller.demo;
 import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.circle.pojo.msg.MessageBean;
import com.circle.utils.msg.MsgQueue;
@Controller
@RequestMapping("demo")
public class DemoController {


@RequestMapping("demomsg.action")
public void demoMsg(ModelAndView mav){
    MessageBean msg = new MessageBean("1", "内容", "1", "1", "参数");
    MsgQueue.GROUP_QUEUE.add(msg);
}


}