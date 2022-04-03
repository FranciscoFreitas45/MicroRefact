package cn.maxcj.core.log.factory;
 import cn.maxcj.modular.system.dao.LoginLogMapper;
import cn.maxcj.modular.system.dao.OperationLogMapper;
import cn.maxcj.modular.system.model.LoginLog;
import cn.maxcj.modular.system.model.OperationLog;
import cn.maxcj.core.common.constant.state.LogSucceed;
import cn.maxcj.core.common.constant.state.LogType;
import cn.maxcj.core.log.LogManager;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import cn.stylefeng.roses.core.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.TimerTask;
public class LogTaskFactory {

 private  Logger logger;

 private  LoginLogMapper loginLogMapper;

 private  OperationLogMapper operationLogMapper;


public TimerTask exceptionLog(Integer userId,Exception exception){
    return new TimerTask() {

        @Override
        public void run() {
            String msg = ToolUtil.getExceptionMsg(exception);
            OperationLog operationLog = LogFactory.createOperationLog(LogType.EXCEPTION, userId, "", null, null, msg, LogSucceed.FAIL);
            try {
                operationLogMapper.insert(operationLog);
            } catch (Exception e) {
                logger.error("创建异常日志异常!", e);
            }
        }
    };
}


public TimerTask bussinessLog(Integer userId,String bussinessName,String clazzName,String methodName,String msg){
    return new TimerTask() {

        @Override
        public void run() {
            OperationLog operationLog = LogFactory.createOperationLog(LogType.BUSSINESS, userId, bussinessName, clazzName, methodName, msg, LogSucceed.SUCCESS);
            try {
                operationLogMapper.insert(operationLog);
            } catch (Exception e) {
                logger.error("创建业务日志异常!", e);
            }
        }
    };
}


@Override
public void run(){
    String msg = ToolUtil.getExceptionMsg(exception);
    OperationLog operationLog = LogFactory.createOperationLog(LogType.EXCEPTION, userId, "", null, null, msg, LogSucceed.FAIL);
    try {
        operationLogMapper.insert(operationLog);
    } catch (Exception e) {
        logger.error("创建异常日志异常!", e);
    }
}


public TimerTask loginLog(String username,String msg,String ip){
    return new TimerTask() {

        @Override
        public void run() {
            LoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN_FAIL, null, "账号:" + username + "," + msg, ip);
            try {
                loginLogMapper.insert(loginLog);
            } catch (Exception e) {
                logger.error("创建登录失败异常!", e);
            }
        }
    };
}


public TimerTask exitLog(Integer userId,String ip){
    return new TimerTask() {

        @Override
        public void run() {
            LoginLog loginLog = LogFactory.createLoginLog(LogType.EXIT, userId, null, ip);
            try {
                loginLogMapper.insert(loginLog);
            } catch (Exception e) {
                logger.error("创建退出日志异常!", e);
            }
        }
    };
}


}