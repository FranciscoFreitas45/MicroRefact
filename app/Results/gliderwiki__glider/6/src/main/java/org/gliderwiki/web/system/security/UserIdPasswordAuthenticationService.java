package org.gliderwiki.web.system.security;
 import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.gliderwiki.framework.exception.AuthenticationNotException;
import org.gliderwiki.framework.exception.PasswordMismatchException;
import org.gliderwiki.framework.exception.UserNotFoundException;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.login.service.LoginService;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
@Service(value = "authenticationService")
public class UserIdPasswordAuthenticationService implements AuthenticationService{

 private Logger logger;

@Resource(name = "loginService")
 private LoginService loginService;

@Autowired
 private MessageSourceAccessor messages;


@Override
public void login(String userId,String password){
    if (StringUtils.isEmpty(userId)) {
        throw new UserNotFoundException(messages.getMessage("account.authenticationFailure"));
    }
    userId = userId.toLowerCase();
    WeUser weUser = new WeUser(userId, password);
    MemberSessionVo result = loginService.getRowWeUserById(userId);
    if (result == null) {
        throw new UserNotFoundException(messages.getMessage("account.authenticationFailure"));
    }
    String passKey = result.getWeUserKey();
    String pwd = loginService.getEncryptPassword(passKey, weUser.getWe_user_pwd());
    if (StringUtil.strNull(result.getWeUserAuthYn()).equals("Y")) {
        if (!result.getWeUserPwd().equals(pwd)) {
            throw new PasswordMismatchException(messages.getMessage("account.passwordmismatch"));
        }
    } else {
        throw new AuthenticationNotException(messages.getMessage("account.notauthentication"));
    }
}


}