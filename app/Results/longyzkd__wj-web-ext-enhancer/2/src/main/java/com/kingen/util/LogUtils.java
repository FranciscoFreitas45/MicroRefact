package com.kingen.util;
 import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.method.HandlerMethod;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kingen.bean.Log;
import com.kingen.bean.Menu;
import com.kingen.bean.User;
import com.kingen.repository.account.MenuDao;
import Interface.MenuDao;
import DTO.User;
@Deprecated
public class LogUtils {

 public  String CACHE_MENU_NAME_PATH_MAP;

 private  MenuDao menuDao;

 private  Log log;

 private  Object handler;

 private  Exception ex;


@Transactional
public void saveLog(HttpServletRequest request,Object handler,Exception ex,String title){
    User user = UserUtils.getCurrentUser();
    if (user != null && user.getUserId() != null) {
        Log log = new Log();
        log.setTitle(title);
        log.setType(ex == null ? Log.TYPE_ACCESS : Log.TYPE_EXCEPTION);
        log.setRemoteAddr(StringUtils.getRemoteAddr(request));
        log.setUserAgent(request.getHeader("user-agent"));
        log.setRequestUri(request.getRequestURI());
        log.setParams(request.getParameterMap());
        // log.setMethod(request.getMethod());
        // 异步保存日志
        new SaveLogThread(log, handler, ex).start();
    }
}


@Override
public void run(){
    // 获取日志标题
    if (StringUtils.isBlank(log.getTitle())) {
        String permission = "";
        if (handler instanceof HandlerMethod) {
            Method m = ((HandlerMethod) handler).getMethod();
            RequiresPermissions rp = m.getAnnotation(RequiresPermissions.class);
            permission = (rp != null ? StringUtils.join(rp.value(), ",") : "");
        }
    // log.setTitle(getMenuNamePath(log.getRequestUri(), permission));
    }
    // 如果有异常，设置异常信息
    log.setException(Exceptions.getStackTraceAsString(ex));
    // 如果无标题并无异常日志，则不保存信息
    if (StringUtils.isBlank(log.getTitle()) && StringUtils.isBlank(log.getException())) {
        return;
    }
// 保存日志信息
// log.preInsert();
// logDao.save(log);
}


}