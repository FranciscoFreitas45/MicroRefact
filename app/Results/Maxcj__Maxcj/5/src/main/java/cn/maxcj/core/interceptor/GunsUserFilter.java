package cn.maxcj.core.interceptor;
 import cn.maxcj.core.shiro.ShiroKit;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class GunsUserFilter extends AccessControlFilter{


public boolean isAccessAllowed(ServletRequest request,ServletResponse response,Object mappedValue){
    if (isLoginRequest(request, response)) {
        return true;
    } else {
        Subject subject = getSubject(request, response);
        // If principal is not null, then the user is known and should be allowed access.
        return subject.getPrincipal() != null;
    }
}


public boolean onAccessDenied(ServletRequest request,ServletResponse response){
    HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
    HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
    /**
     * 如果是ajax请求则不进行跳转
     */
    if (httpServletRequest.getHeader("x-requested-with") != null && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
        httpServletResponse.setHeader("sessionstatus", "timeout");
        return false;
    } else {
        /**
         * 第一次点击页面
         */
        String referer = httpServletRequest.getHeader("Referer");
        if (referer == null) {
            saveRequestAndRedirectToLogin(request, response);
            return false;
        } else {
            /**
             * 从别的页面跳转过来的
             */
            if (ShiroKit.getSession().getAttribute("sessionFlag") == null) {
                httpServletRequest.setAttribute("tips", "session超时");
                httpServletRequest.getRequestDispatcher("/login").forward(request, response);
                return false;
            } else {
                saveRequestAndRedirectToLogin(request, response);
                return false;
            }
        }
    }
}


}