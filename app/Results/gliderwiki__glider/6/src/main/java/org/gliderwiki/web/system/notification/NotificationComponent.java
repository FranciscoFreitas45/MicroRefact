package org.gliderwiki.web.system.notification;
 import javax.annotation.Resource;
import org.gliderwiki.framework.util.Base64Coder;
import org.gliderwiki.framework.util.SecretKeyPBECipher;
import org.gliderwiki.framework.util.SimpleAesStringCipherManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
@Component
public class NotificationComponent {

 private Logger logger;

@Value("#{config['alarm.key']}")
 private String alarmKey;

@Resource(name = "redisTemplate")
 private  RedisTemplate<String,String> redisTemplate;

@Autowired
 private  SimpleAesStringCipherManager simpleAesStringCipherManager;


@Async
public void sendNotification(Integer userIdx,String message){
    try {
        logger.debug("#################redis call##################");
        logger.debug("userIdx : {}", userIdx);
        logger.debug("message : {}", message);
        logger.debug("#################redis call##################");
        redisTemplate.convertAndSend(simpleAesStringCipherManager.encrypt(String.format("A_%d", userIdx)), message);
        logger.debug("alarm send success!!");
    } catch (Exception e) {
        logger.debug("alarm send fail!!");
    }
}


}