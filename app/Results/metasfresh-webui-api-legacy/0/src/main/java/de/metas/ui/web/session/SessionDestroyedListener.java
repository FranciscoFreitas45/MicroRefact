package de.metas.ui.web.session;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.stereotype.Component;
import de.metas.ui.web.notification.UserNotificationsService;
@Component
public class SessionDestroyedListener implements ApplicationListener<SessionDestroyedEvent>{

@Autowired
 private  UserNotificationsService userNotificationsService;


@Override
public void onApplicationEvent(SessionDestroyedEvent event){
    final String sessionId = event.getSessionId();
    userNotificationsService.disableForSession(sessionId);
}


}