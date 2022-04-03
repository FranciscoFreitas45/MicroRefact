package com.example.steam.mq.eventhandle;
 import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.utils.EmailUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
@Component
public class SendEmailVerificationCodeEventHandle implements EventHandle{


@Override
public List<EventType> bindEventType(){
    return Arrays.asList(EventType.SEND_EMAIL_VERIFICATION_CODE);
}


@Override
public void eventHandle(Event event,ApplicationContext applicationContext){
    EmailUtil emailUtil = (EmailUtil) applicationContext.getBean("emailUtil");
    emailUtil.sendVerificationCode((String) event.getEtrMsg().get(Event.EMAIL));
}


}