package edu.xr.campusweibo.web.rest;
 import edu.xr.campusweibo.config.Constants;
import edu.xr.campusweibo.domain.MyUser;
import edu.xr.campusweibo.service.LoginService;
import edu.xr.campusweibo.service.MyUserService;
import edu.xr.campusweibo.web.rest.util.ResponseData;
import edu.xr.campusweibo.web.rest.util.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation;
@RestController
@RequestMapping(value = "/weibo")
public class MyUserResource {

 private  Logger log;

@Autowired
 private  MyUserService myUserService;


@RequestMapping(value = "/user/find/{id}", method = RequestMethod.GET)
public ResponseResult getUserInfoById(Long id){
    log.info("开始查询用户详细信息,用户ID为 ： {}", id);
    MyUser myUser = null;
    try {
        myUser = myUserService.getUserById(id);
    } catch (Exception e) {
        log.info("查询用户信息失败，用户ID为：{}", id);
        return new ResponseResult(Constants.FAIL_CODE, Constants.USER_QUARY_EXP);
    }
    return new ResponseData<MyUser>(Constants.SUCCESS_CODE, Constants.SUCCESS_INFO, myUser);
}


@RequestMapping(value = "/user/addOrUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseResult createOrUpdateUser(MyUser myUser){
    log.info("创建或更新用户=" + myUser.getSchoolcode());
    MyUser newUser = myUserService.getUserBySchoolcode(myUser.getSchoolcode());
    if (newUser != null) {
        // 用户已存在
        if (myUser.getId() == null) {
            return new ResponseResult(Constants.FAIL_CODE, Constants.USER_ADD_EXIST);
        } else {
            // 更新用户
            try {
                myUserService.saveUser(myUser);
                return new ResponseResult(Constants.SUCCESS_CODE, Constants.SUCCESS_INFO);
            } catch (Exception e) {
                log.info("更新用户异常");
                return new ResponseResult(Constants.FAIL_CODE, Constants.USER_ADD_UPDATE_EXP);
            }
        }
    }
    // 创建用户
    try {
        myUserService.saveUser(myUser);
        return new ResponseResult(Constants.SUCCESS_CODE, Constants.SUCCESS_INFO);
    } catch (Exception e) {
        log.info("创建新用户异常");
        return new ResponseResult(Constants.FAIL_CODE, Constants.USER_ADD_UPDATE_EXP);
    }
}


}