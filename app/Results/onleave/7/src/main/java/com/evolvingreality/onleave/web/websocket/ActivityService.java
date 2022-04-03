package com.evolvingreality.onleave.web.websocket;
 import com.evolvingreality.onleave.security.SecurityUtils;
import com.evolvingreality.onleave.web.websocket.dto.ActivityDTO;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import javax.inject.Inject;
import java.security.Principal;
import java.util.Calendar;
import com.evolvingreality.onleave.config.WebsocketConfiguration.IP_ADDRESS;
@Controller
public class ActivityService implements ApplicationListener<SessionDisconnectEvent>{

 private  Logger log;

 private  DateTimeFormatter dateTimeFormatter;

@Inject
 private SimpMessageSendingOperations messagingTemplate;


@Override
public void onApplicationEvent(SessionDisconnectEvent event){
    ActivityDTO activityDTO = new ActivityDTO();
    activityDTO.setSessionId(event.getSessionId());
    activityDTO.setPage("logout");
    messagingTemplate.convertAndSend("/topic/tracker", activityDTO);
}


@SubscribeMapping("/topic/activity")
@SendTo("/topic/tracker")
public ActivityDTO sendActivity(ActivityDTO activityDTO,StompHeaderAccessor stompHeaderAccessor,Principal principal){
    activityDTO.setUserLogin(SecurityUtils.getCurrentLogin());
    activityDTO.setUserLogin(principal.getName());
    activityDTO.setSessionId(stompHeaderAccessor.getSessionId());
    activityDTO.setIpAddress(stompHeaderAccessor.getSessionAttributes().get(IP_ADDRESS).toString());
    activityDTO.setTime(dateTimeFormatter.print(Calendar.getInstance().getTimeInMillis()));
    log.debug("Sending user tracking data {}", activityDTO);
    return activityDTO;
}


}