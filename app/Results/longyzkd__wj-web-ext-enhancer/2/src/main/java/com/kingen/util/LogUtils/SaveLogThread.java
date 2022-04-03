package com.kingen.util.LogUtils;
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
public class SaveLogThread extends Thread{

 private  Log log;

 private  Object handler;

 private  Exception ex;

public SaveLogThread(Log log, Object handler, Exception ex) {
    super(SaveLogThread.class.getSimpleName());
    this.log = log;
    this.handler = handler;
    this.ex = ex;
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