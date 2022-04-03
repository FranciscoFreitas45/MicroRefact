package com.dtdhehe.ptulife.controller;
 import com.dtdhehe.ptulife.entity.PtuUser;
import com.dtdhehe.ptulife.service.UserService;
import com.dtdhehe.ptulife.util.PasswordUtils;
import com.dtdhehe.ptulife.util.RedisUtils;
import com.dtdhehe.ptulife.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
@RequestMapping("/login/loginController")
public class LoginController {

 private  Logger logger;

@Autowired
 private  UserService userService;

@Resource
 private  RedisUtils redisUtils;


@RequestMapping("/logout")
public String logout(HttpServletRequest request){
    logger.info("清除session中登录的用户");
    HttpSession session = request.getSession();
    if (session != null) {
        PtuUser ptuUser = (PtuUser) request.getSession().getAttribute("loginUser");
        session.removeAttribute("loginUser");
        // 清空redis中的用户
        redisUtils.del(ptuUser.getUserId());
    }
    return "login";
}


@RequestMapping("/login")
@ResponseBody
public ResultVO login(HttpServletRequest request){
    ResultVO resultVO = new ResultVO();
    logger.info("用户登录");
    String userName = request.getParameter("username");
    String realPwd = request.getParameter("password");
    logger.info("用户输入的用户名和密码:" + userName + ";" + realPwd);
    // 对密码进行加密
    String userPwd = PasswordUtils.getPWD(realPwd);
    logger.info("用户加密后的密码:" + userPwd);
    PtuUser ptuUser = userService.findByUserNameAndUserPwd(userName, userPwd);
    if (ptuUser != null) {
        if ("0".equals(ptuUser.getValid())) {
            resultVO.setStatus("1");
            resultVO.setError_msg("该用户尚未激活");
            return resultVO;
        }
        request.getSession().setAttribute("loginUser", ptuUser);
        boolean redisFlag = redisUtils.set(ptuUser.getUserId(), ptuUser);
        if (redisFlag) {
            resultVO.setStatus("0");
            resultVO.setObject(ptuUser);
        } else {
            resultVO.setStatus("1");
            resultVO.setError_msg("Redis服务异常");
        }
    } else {
        logger.error("用户名或密码错误");
        resultVO.setStatus("1");
        resultVO.setError_msg("用户名或密码错误");
    }
    return resultVO;
}


}