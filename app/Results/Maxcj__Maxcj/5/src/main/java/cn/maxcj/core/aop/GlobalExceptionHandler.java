package cn.maxcj.core.aop;
 import cn.maxcj.core.common.exception.BizExceptionEnum;
import cn.maxcj.core.common.exception.InvalidKaptchaException;
import cn.maxcj.core.log.LogManager;
import cn.maxcj.core.log.factory.LogTaskFactory;
import cn.maxcj.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.DisabledAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.lang.reflect.UndeclaredThrowableException;
import cn.stylefeng.roses.core.util.HttpContext.getIp;
import cn.stylefeng.roses.core.util.HttpContext.getRequest;
@ControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {

 private  Logger log;


@ExceptionHandler(DisabledAccountException.class)
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public String accountLocked(DisabledAccountException e,Model model){
    String username = getRequest().getParameter("username");
    LogManager.me().executeLog(LogTaskFactory.loginLog(username, "账号被冻结", getIp()));
    model.addAttribute("tips", "账号被冻结");
    return "/login.html";
}


@ExceptionHandler(AuthenticationException.class)
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public String unAuth(AuthenticationException e){
    log.error("用户未登陆：", e);
    return "/login.html";
}


@ExceptionHandler(UndeclaredThrowableException.class)
@ResponseStatus(HttpStatus.UNAUTHORIZED)
@ResponseBody
public ErrorResponseData credentials(UndeclaredThrowableException e){
    getRequest().setAttribute("tip", "权限异常");
    log.error("权限异常!", e);
    return new ErrorResponseData(BizExceptionEnum.NO_PERMITION.getCode(), BizExceptionEnum.NO_PERMITION.getMessage());
}


@ExceptionHandler(RuntimeException.class)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@ResponseBody
public ErrorResponseData notFount(RuntimeException e){
    LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
    getRequest().setAttribute("tip", "服务器未知运行时异常");
    log.error("运行时异常:", e);
    return new ErrorResponseData(BizExceptionEnum.SERVER_ERROR.getCode(), BizExceptionEnum.SERVER_ERROR.getMessage());
}


@ExceptionHandler(ServiceException.class)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@ResponseBody
public ErrorResponseData bussiness(ServiceException e){
    LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
    getRequest().setAttribute("tip", e.getMessage());
    log.error("业务异常:", e);
    return new ErrorResponseData(e.getCode(), e.getMessage());
}


}