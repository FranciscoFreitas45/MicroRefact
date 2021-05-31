import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import cn.offway.athena.domain.PhAdmin;
import cn.offway.athena.service.RbacService;
@Component("rbacService")
public class RbacServiceImpl implements cn.offway.athena.service.RbacService,RbacService{

 private  AntPathMatcher antPathMatcher;


@Override
public boolean hasPermission(HttpServletRequest request,Authentication authentication){
    Object principal = authentication.getPrincipal();
    boolean hasPermission = false;
    if (principal instanceof PhAdmin) {
        // 如果用户名是admin，就永远返回true
        if (StringUtils.equals(((PhAdmin) principal).getUsername(), "admin")) {
            hasPermission = true;
        } else {
            // 只过滤页面入口
            if (request.getRequestURI().indexOf(".html") > -1) {
                // 读取用户所拥有权限的所有URL
                Set<String> urls = ((PhAdmin) principal).getUrls();
                for (String url : urls) {
                    if (antPathMatcher.match(url, request.getRequestURI())) {
                        hasPermission = true;
                        break;
                    }
                }
            } else {
                hasPermission = true;
            }
        }
    }
    return hasPermission;
}


}