package com.netease.controller.api;
 import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.netease.service.SecurityUpgradeService;
@Controller
public class SecurityUpgradeController {

 final  Logger logger;

@Autowired
 private SecurityUpgradeService securityUpgradeService;


@SuppressWarnings("unused")
@RequestMapping(value = "/Credit/security", method = RequestMethod.GET)
public void getQuestion(HttpServletRequest request,HttpServletResponse response,String loginname,String password,String verifyCode,Model model){
    // 时间充足的情况下需要对参数做一些列的基本校验，目前没时间做这个校验工作
    String questionString = null;
    try {
        questionString = securityUpgradeService.getQuestionContent(loginname, password, verifyCode);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}