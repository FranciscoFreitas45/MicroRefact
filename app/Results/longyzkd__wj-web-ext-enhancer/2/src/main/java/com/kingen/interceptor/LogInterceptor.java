package com.kingen.interceptor;
 import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.kingen.util.DateUtils;
import com.kingen.util.LogUtils;
@Deprecated
public class LogInterceptor implements HandlerInterceptor{

 protected  Logger logger;

 private  ThreadLocal<Long> startTimeThreadLocal;


@Override
public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView){
    if (modelAndView != null) {
        logger.info("ViewName: " + modelAndView.getViewName());
    }
}


@Override
public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){
    // 保存日志
    LogUtils.saveLog(request, handler, ex, null);
    // 打印JVM信息。
    if (logger.isDebugEnabled()) {
        // 得到线程绑定的局部变量（开始时间）
        long beginTime = startTimeThreadLocal.get();
        // 2、结束时间
        long endTime = System.currentTimeMillis();
        logger.debug("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m", new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), DateUtils.formatDateTime(endTime - beginTime), request.getRequestURI(), Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024, (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        // 删除线程变量中的数据，防止内存泄漏
        startTimeThreadLocal.remove();
    }
}


@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler){
    if (logger.isDebugEnabled()) {
        // 1、开始时间
        long beginTime = System.currentTimeMillis();
        // 线程绑定变量（该数据只有当前请求的线程可见）
        startTimeThreadLocal.set(beginTime);
        logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS").format(beginTime), request.getRequestURI());
    }
    return true;
}


}