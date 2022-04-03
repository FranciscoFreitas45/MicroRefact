package com.dtdhehe.ptulife.controller;
 import com.dtdhehe.ptulife.entity.PtuUser;
import com.dtdhehe.ptulife.service.MailService;
import com.dtdhehe.ptulife.service.UserService;
import com.dtdhehe.ptulife.util.MailUtils;
import com.dtdhehe.ptulife.util.PasswordUtils;
import com.dtdhehe.ptulife.vo.ResultVO;
import com.dtdhehe.ptulife.vo.UserRegistVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import com.dtdhehe.ptulife.Interface.MailService;
@Controller
@RequestMapping("/ptu/registUserController")
public class RegistUserController {

 private  Logger logger;

@Autowired
 private  UserService userService;

@Autowired
 private  MailService mailService;


@RequestMapping("/validUser")
public String validUser(String userId){
    // 激活用户
    try {
        if (!StringUtils.isEmpty(userId)) {
            PtuUser ptuUser = userService.findByUserId(userId);
            ptuUser.setValid("1");
            userService.update(ptuUser);
        }
    } catch (Exception e) {
        logger.error(e.getMessage());
    }
    return "index/login";
}


@RequestMapping("/regist")
@ResponseBody
public ResultVO regist(UserRegistVO userRegistVO,HttpServletRequest request){
    logger.info("传入的对象为：" + userRegistVO);
    PtuUser resultUser;
    ResultVO resultVO = new ResultVO();
    try {
        logger.info("用户输入密码为:" + userRegistVO.getUserPwd());
        // 给用户密码加密
        userRegistVO.setUserPwd(PasswordUtils.getPWD(userRegistVO.getUserPwd()));
        logger.info("用户密码加密后为:" + userRegistVO.getUserPwd());
        resultUser = userService.save(userRegistVO);
        logger.info("处理后的用户为:" + resultUser);
        // 判断存入是否成功，封装返回对象
        if (resultUser != null) {
            // 注册成功后发送邮件
            String htmls = MailUtils.getValidHtml(resultUser.getUserId(), request);
            mailService.sendHtmlMail(resultUser.getEmail(), "激活邮件", htmls);
            resultVO.setStatus("0");
            resultVO.setError_msg("注册成功");
        }
    } catch (Exception e) {
        logger.error(e.getMessage());
        resultVO.setStatus("1");
        resultVO.setError_msg("注册失败");
    }
    return resultVO;
}


}