package com.zis.shiro.filter;
 import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.alibaba.druid.support.json.JSONUtils;
public class ShiroFilterUtils {

 private  Logger logger;


public boolean isAjax(ServletRequest request){
    String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
    if ("XMLHttpRequest".equalsIgnoreCase(header)) {
        // log
        return Boolean.TRUE;
    }
    // log
    return Boolean.FALSE;
}


public void out(ServletResponse response,Object result){
    PrintWriter out = null;
    try {
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        // 设置返回类型
        response.setContentType("application/json");
        out = response.getWriter();
        // 输出
        out.println(JSONUtils.toJSONString(result));
    } catch (Exception e) {
        logger.error("输出JSON报错", e);
    // LoggerUtils.fmtError(CLAZZ, e, "输出JSON报错。");
    } finally {
        if (null != out) {
            out.flush();
            out.close();
        }
    }
}


}