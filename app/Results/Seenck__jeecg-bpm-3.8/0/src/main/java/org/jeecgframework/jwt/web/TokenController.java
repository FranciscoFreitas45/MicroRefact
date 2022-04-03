package org.jeecgframework.jwt.web;
 import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.service.TokenManager;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import Interface.TokenManager;
@Api(value = "token", description = "鉴权token接口", tags = "tokenAPI")
@Controller
@RequestMapping("/tokens")
public class TokenController {

 private  Logger logger;

@Autowired
 private  UserService userService;

@Autowired
 private  TokenManager tokenManager;


@ApiOperation(value = "销毁TOKEN")
@RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
@ResponseBody
public ResponseMessage<?> logout(String username){
    logger.info("deleteToken[{}]", username);
    // 验证
    if (StringUtils.isEmpty(username)) {
        return Result.error("用户账号，不能为空!");
    }
    try {
        tokenManager.deleteToken(username);
    } catch (Exception e) {
        e.printStackTrace();
        return Result.error("销毁TOKEN失败");
    }
    return Result.success();
}


@ApiOperation(value = "获取TOKEN")
@RequestMapping(method = RequestMethod.POST)
@ResponseBody
public ResponseEntity<?> login(String username,String password){
    logger.info("获取TOKEN[{}]", username);
    // 验证
    if (StringUtils.isEmpty(username)) {
        return new ResponseEntity("用户账号不能为空!", HttpStatus.NOT_FOUND);
    }
    // 验证
    if (StringUtils.isEmpty(username)) {
        return new ResponseEntity("用户密码不能为空!", HttpStatus.NOT_FOUND);
    }
    Assert.notNull(username, "username can not be empty");
    Assert.notNull(password, "password can not be empty");
    TSUser user = userService.checkUserExits(username, password);
    if (user == null) {
        // 提示用户名或密码错误
        logger.info("获取TOKEN,户账号密码错误[{}]", username);
        return new ResponseEntity("用户账号密码错误!", HttpStatus.NOT_FOUND);
    }
    // 生成一个token，保存用户登录状态
    String token = tokenManager.createToken(user);
    return new ResponseEntity(token, HttpStatus.OK);
}


}