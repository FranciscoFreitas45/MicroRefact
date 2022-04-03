package org.sdrc.core;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sdrc.childinfo.model.FeaturePermissionMapping;
import org.sdrc.childinfo.model.User;
import org.sdrc.childinfo.util.Constants;
import org.sdrc.childinfo.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.sdrc.Interface.StateManager;
@Component
public class AuthorizeInterceptor extends HandlerInterceptorAdapter{

 private  StateManager stateManager;

 private  ResourceBundleMessageSource messageSource;

@Autowired
public AuthorizeInterceptor(StateManager stateManager, ResourceBundleMessageSource messageSource) {
    this.stateManager = stateManager;
    this.messageSource = messageSource;
}
@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler){
    /*
		 * Added to cleanup the error list in the SessionStateManager.
		 * Required to remove the previous errors added, on subsequent requests.
		 */
    if (stateManager.getError() != null && !(stateManager.getError().isEmpty())) {
        stateManager.setError(null);
    }
    if (!(handler instanceof HandlerMethod))
        return true;
    Authorize authorize = ((HandlerMethod) handler).getMethodAnnotation(Authorize.class);
    if (authorize == null)
        return true;
    User user = (User) stateManager.getValue(Constants.USER_PRINCIPAL);
    if (user == null)
        throw new AccessDeniedException(messageSource.getMessage(Constants.ACCESS_DENIED, null, null));
    String feature = authorize.feature();
    String permission = authorize.permission();
    if (user != null && user.getUserRoleSchemeMappings() != null) {
        for (int i = 0; i < user.getUserRoleSchemeMappings().size(); i++) {
            FeaturePermissionMapping fpMapping = user.getUserRoleSchemeMappings().get(i).getRoleScheme().getFeaturePermissionMapping();
            if (feature.equals(fpMapping.getFeature().getFeatureName()) && permission.equals(fpMapping.getPermission().getPermissionName())) {
                return true;
            }
        }
    }
    throw new AccessDeniedException(messageSource.getMessage(Constants.ACCESS_DENIED, null, null));
}


}