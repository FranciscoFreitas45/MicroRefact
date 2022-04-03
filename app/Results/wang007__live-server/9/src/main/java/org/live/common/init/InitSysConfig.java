package org.live.common.init;
 import org.live.common.shiro.RetryLimitHashedCredentialsMatcher;
import org.live.common.systemlog.SystemLogAspect;
import org.live.config.dataComponent.SystemConfigDataComponent;
import org.live.websocket.chat.ChatHallManager;
import org.live.websocket.chat.OnChatListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
@Component
public class InitSysConfig implements ApplicationListener<ContextRefreshedEvent>{

 private  Logger LOGGER;

@Resource
 private  SystemConfigDataComponent dataComponent;


@Override
public void onApplicationEvent(ContextRefreshedEvent event){
    ApplicationContext context = event.getApplicationContext();
    if (context.getParent() == null) {
        // 确保是spring 主容器启动
        LOGGER.info("执行相关参数的设置。包括密码重试次数，日志切面");
        // 得到shiro的凭证匹配器
        RetryLimitHashedCredentialsMatcher matcher = context.getBean(RetryLimitHashedCredentialsMatcher.class);
        if (dataComponent.getPasswordRetryCount() != 0) {
            // 设置密码重试次数
            matcher.setRetryLimitCount(dataComponent.getPasswordRetryCount());
        }
        SystemLogAspect aspectComponent = context.getBean(SystemLogAspect.class);
        String logLevel = dataComponent.getLogLevel() == null || "".equals(dataComponent.getLogLevel()) ? "info" : dataComponent.getLogLevel();
        if ("INFO".equalsIgnoreCase(logLevel)) {
            aspectComponent.setLogLevel(1);
        } else if ("WARN".equalsIgnoreCase(logLevel)) {
            aspectComponent.setLogLevel(2);
        } else {
            aspectComponent.setLogLevel(3);
        }
        LOGGER.info("为ChatHallManager设置监听器");
        OnChatListener chatListener = context.getBean(OnChatListener.class);
        ChatHallManager.setupListener(chatListener);
    }
}


}