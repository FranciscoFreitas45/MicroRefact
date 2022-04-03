package cn.maxcj.modular.api;
 import cn.maxcj.modular.system.dao.UserMapper;
import cn.maxcj.modular.system.model.User;
import cn.maxcj.core.shiro.ShiroKit;
import cn.maxcj.core.shiro.ShiroUser;
import cn.maxcj.core.util.JwtTokenUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
@RestController
@RequestMapping("/gunsApi")
public class ApiController extends BaseController{

@Autowired
 private  UserMapper userMapper;


@RequestMapping(value = "/test", method = RequestMethod.POST)
public Object test(){
    return SUCCESS_TIP;
}


@RequestMapping("/auth")
public Object auth(String username,String password){
    // 封装请求账号密码为shiro可验证的token
    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray());
    // 获取数据库中的账号密码，准备比对
    User user = userMapper.getByAccount(username);
    String credentials = user.getPassword();
    String salt = user.getSalt();
    ByteSource credentialsSalt = new Md5Hash(salt);
    SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(new ShiroUser(), credentials, credentialsSalt, "");
    // 校验用户账号密码
    HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
    md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
    md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
    boolean passwordTrueFlag = md5CredentialsMatcher.doCredentialsMatch(usernamePasswordToken, simpleAuthenticationInfo);
    if (passwordTrueFlag) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("token", JwtTokenUtil.generateToken(String.valueOf(user.getId())));
        return result;
    } else {
        return new ErrorResponseData(500, "账号密码错误！");
    }
}


}