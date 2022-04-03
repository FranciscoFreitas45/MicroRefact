package org.live.common.shiro;
 import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
public class LogoutSessionListener extends SessionListenerAdapter{

 private  Logger logger;

@Resource
 private  LoginRealm loginRealm;


@Override
public void onExpiration(Session session){
    try {
        // 获取身份信息
        PrincipalCollection principals = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (!CollectionUtils.isEmpty(principals)) {
            logger.debug("清除session并同时清除 [{}] 认证和授权缓存", principals.getPrimaryPrincipal());
            // 清楚用户认证和授权缓存。
            loginRealm.clearCache(principals);
        }
        super.onExpiration(session);
    } catch (Exception e) {
        logger.error("session过期操作异常！", e);
    }
}


}