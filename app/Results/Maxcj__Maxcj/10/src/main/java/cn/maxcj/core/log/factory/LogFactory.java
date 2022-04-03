package cn.maxcj.core.log.factory;
 import cn.maxcj.modular.system.model.LoginLog;
import cn.maxcj.modular.system.model.OperationLog;
import cn.maxcj.core.common.constant.state.LogSucceed;
import cn.maxcj.core.common.constant.state.LogType;
import java.util.Date;
public class LogFactory {


public LoginLog createLoginLog(LogType logType,Integer userId,String msg,String ip){
    LoginLog loginLog = new LoginLog();
    loginLog.setLogname(logType.getMessage());
    loginLog.setUserid(userId);
    loginLog.setCreatetime(new Date());
    loginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
    loginLog.setIp(ip);
    loginLog.setMessage(msg);
    return loginLog;
}


public OperationLog createOperationLog(LogType logType,Integer userId,String bussinessName,String clazzName,String methodName,String msg,LogSucceed succeed){
    OperationLog operationLog = new OperationLog();
    operationLog.setLogtype(logType.getMessage());
    operationLog.setLogname(bussinessName);
    operationLog.setUserid(userId);
    operationLog.setClassname(clazzName);
    operationLog.setMethod(methodName);
    operationLog.setCreatetime(new Date());
    operationLog.setSucceed(succeed.getMessage());
    operationLog.setMessage(msg);
    return operationLog;
}


}