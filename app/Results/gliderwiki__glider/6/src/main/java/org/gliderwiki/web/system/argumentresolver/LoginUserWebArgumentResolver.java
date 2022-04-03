package org.gliderwiki.web.system.argumentresolver;
 import java.lang.annotation.Annotation;
import javax.servlet.http.HttpServletRequest;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.web.system.SessionService;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
public class LoginUserWebArgumentResolver implements WebArgumentResolver{

 private Logger logger;


@Override
public Object resolveArgument(MethodParameter param,NativeWebRequest req){
    Annotation[] paramAnns = param.getParameterAnnotations();
    for (Annotation paramAnn : paramAnns) {
        if (LoginUser.class.isInstance(paramAnn) && paramAnn.annotationType().equals(LoginUser.class)) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) req.getNativeRequest();
            MemberSessionVo loginUser = (MemberSessionVo) httpServletRequest.getAttribute("loginUser");
            logger.debug("LoginUserWebArgumentResolver : {}", loginUser);
            if (loginUser == null) {
                loginUser = MemberSessionVo.GUEST_USER.getGuestUser();
            }
            return loginUser;
        }
    }
    return UNRESOLVED;
}


}