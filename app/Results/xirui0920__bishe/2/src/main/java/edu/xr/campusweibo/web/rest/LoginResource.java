package edu.xr.campusweibo.web.rest;
 import edu.xr.campusweibo.config.Constants;
import edu.xr.campusweibo.domain.Friend;
import edu.xr.campusweibo.domain.MyReply;
import edu.xr.campusweibo.domain.MyUser;
import edu.xr.campusweibo.domain.Weibo;
import edu.xr.campusweibo.service.FriendService;
import edu.xr.campusweibo.service.LoginService;
import edu.xr.campusweibo.service.MyReplyService;
import edu.xr.campusweibo.service.WeiboService;
import edu.xr.campusweibo.service.dto.WeiboDTO;
import edu.xr.campusweibo.web.rest.util.ResponseData;
import edu.xr.campusweibo.web.rest.util.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.xr.campusweibo.Interface.WeiboService;
import edu.xr.campusweibo.Interface.MyReplyService;
@RestController
@RequestMapping("/weibo")
public class LoginResource {

 private  Logger log;

 private  LoginService loginService;

 private  WeiboService weiboService;

 private  FriendService friendService;

 private  MyReplyService myReplyService;

public LoginResource(LoginService loginService, WeiboService weiboService, FriendService friendService, MyReplyService myReplyService) {
    this.loginService = loginService;
    this.weiboService = weiboService;
    this.friendService = friendService;
    this.myReplyService = myReplyService;
}
@RequestMapping(value = "/logout", method = RequestMethod.POST)
@ResponseBody
public ResponseResult logout(HttpServletRequest request,HttpServletResponse response){
    log.info("user logout ..............................");
    try {
        request.getSession().invalidate();
    } catch (Exception e) {
        log.error("user logout appear exception!", e);
        return new ResponseResult(Constants.FAIL_CODE, Constants.LOGOUT_OCCUR_EXCEPTION);
    }
    return new ResponseResult(Constants.SUCCESS_CODE, Constants.SUCCESS_INFO);
}


@RequestMapping(value = "/login", method = RequestMethod.POST)
@ResponseBody
public Map<String,Object> login(HttpServletRequest request,HttpServletResponse response){
    log.info("用户开始登陆...................");
    Map<String, Object> returnMap = new HashMap<>();
    String schoolcode = request.getParameter("schoolcode");
    String password = request.getParameter("password");
    if (!StringUtils.hasText(schoolcode) || !StringUtils.hasText(password)) {
        log.info("login信息异常");
        returnMap.put("returnCode", Constants.FAIL_CODE);
        returnMap.put("returnInfo", Constants.LOGIN_USERNAME_OR_PASSWORD_IS_BLANK);
        return returnMap;
    }
    log.info("开始登陆，登陆学号为 = " + schoolcode);
    MyUser u = null;
    try {
        u = loginService.findBySchoolcodeAndPassword(schoolcode, password);
    } catch (Exception e) {
        log.info("登陆异常:", e);
        returnMap.put("returnCode", Constants.FAIL_CODE);
        returnMap.put("returnInfo", Constants.LOGIN_OCCUR_EXCEPTION);
        return returnMap;
    }
    if (u == null) {
        log.info("登陆异常");
        returnMap.put("returnCode", Constants.FAIL_CODE);
        returnMap.put("returnInfo", Constants.LOGIN_USER_NOT);
        return returnMap;
    }
    log.info("用户登陆成功.....");
    request.getSession().setAttribute(Constants.SEESION_STORE_USERINFO_KEY, u);
    returnMap.put("returnCode", Constants.SUCCESS_CODE);
    returnMap.put("returnInfo", Constants.SUCCESS_INFO);
    returnMap.put("data", u);
    returnMap.put("weibonum", weiboService.getWeiboNum(u.getId()));
    returnMap.put("frenum", friendService.getFridNum(u.getId()));
    List<Friend> friendList = friendService.getAllFrid(u.getId());
    List<WeiboDTO> listDTO = new ArrayList<>();
    List<MyReply> myReplyList = null;
    for (int i = 0; i < friendList.size(); i++) {
        List<Weibo> newweiboList = weiboService.getAllWeibo(friendList.get(i).getFuid());
        for (int j = 0; j < newweiboList.size(); j++) {
            myReplyList = myReplyService.getAllReply(newweiboList.get(j).getId());
            listDTO.add(new WeiboDTO(newweiboList.get(j), loginService.getUserById(friendList.get(i).getFuid()).getNickname(), loginService.getUserById(friendList.get(i).getFuid()).getImage_url(), myReplyList));
        }
    }
    returnMap.put("weiboinfo", listDTO);
    return returnMap;
}


}