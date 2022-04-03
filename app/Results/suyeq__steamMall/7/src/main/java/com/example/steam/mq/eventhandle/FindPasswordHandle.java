package com.example.steam.mq.eventhandle;
 import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.utils.EmailUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
@Component
public class FindPasswordHandle implements EventHandle{


@Override
public List<EventType> bindEventType(){
    return Arrays.asList(EventType.FIND_WORD);
}


@Override
public void eventHandle(Event event,ApplicationContext applicationContext){
    String email = (String) event.getEtrMsg().get(Event.EMAIL);
    String newPassword = (String) event.getEtrMsg().get(Event.NEW_WORD);
    ((EmailUtil) applicationContext.getBean("emailUtil")).sendFindPassword(email, newPassword);
}


}