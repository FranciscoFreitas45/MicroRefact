package cn.maxcj.modular.system.controller;
 import cn.maxcj.modular.system.service.INoticeService;
import cn.maxcj.modular.system.service.IUserService;
import cn.maxcj.modular.system.warpper.UserWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Map;
import cn.maxcj.Interface.INoticeService;
@Controller
@RequestMapping("/blackboard")
public class BlackboardController extends BaseController{

@Autowired
 private  INoticeService noticeService;

@Autowired
 private  IUserService userService;


@RequestMapping("/notice")
public String blackboard(Model model){
    List<Map<String, Object>> notices = noticeService.list(null);
    model.addAttribute("noticeList", notices);
    return "/noticelist.html";
}


@RequestMapping("")
public String overview(){
    return "/overview.html";
}


@RequestMapping("/file")
public String fileManage(Model model){
    return "/system/file/file.html";
}


@RequestMapping("/contacts")
public String contacts(Model model){
    List<Map<String, Object>> users = userService.selectSheLian();
    model.addAttribute("shelian", new UserWarpper(users).wrap());
    return "/contacts.html";
}


}