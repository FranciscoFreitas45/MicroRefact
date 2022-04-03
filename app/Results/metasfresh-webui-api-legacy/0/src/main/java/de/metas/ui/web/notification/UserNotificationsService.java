package de.metas.ui.web.notification;
 import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import de.metas.event.Event;
import de.metas.event.IEventBus;
import de.metas.event.IEventBusFactory;
import de.metas.logging.LogManager;
import de.metas.notification.INotificationRepository;
import de.metas.notification.UserNotification;
import de.metas.notification.UserNotificationUtils;
import de.metas.notification.UserNotificationsList;
import de.metas.ui.web.session.UserSession.LanguagedChangedEvent;
import de.metas.ui.web.websocket.WebsocketSender;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.user.UserId;
import de.metas.util.Services;
import lombok.NonNull;
@Service
public class UserNotificationsService {

 private  Logger logger;

@Autowired
 private  WebsocketSender websocketSender;

 private  ConcurrentHashMap<UserId,UserNotificationsQueue> adUserId2notifications;

 private  AtomicBoolean subscribedToEventBus;


public void markAllNotificationsAsRead(UserId adUserId){
    getNotificationsQueue(adUserId).markAllAsRead();
}


public void deleteAllNotification(UserId adUserId){
    getNotificationsQueue(adUserId).deleteAll();
}


public UserNotificationsList getNotifications(UserId adUserId,int limit){
    return getNotificationsQueue(adUserId).getNotificationsAsList(limit);
}


public void markNotificationAsRead(UserId adUserId,String notificationId){
    getNotificationsQueue(adUserId).markAsRead(notificationId);
}


public void deleteNotification(UserId adUserId,String notificationId){
    getNotificationsQueue(adUserId).delete(notificationId);
}


public void subscribeToEventTopicsIfNeeded(){
    if (!subscribedToEventBus.getAndSet(true)) {
        final IEventBusFactory eventBusFactory = Services.get(IEventBusFactory.class);
        eventBusFactory.registerUserNotificationsListener(this::forwardEventToNotificationsQueues);
    }
}


public String getWebsocketEndpoint(UserId adUserId){
    return getNotificationsQueue(adUserId).getWebsocketEndpoint();
}


public void forwardEventToNotificationsQueues(IEventBus eventBus,Event event){
    logger.trace("Got event from {}: {}", eventBus, event);
    final UserNotification notification = UserNotificationUtils.toUserNotification(event);
    final UserId recipientUserId = UserId.ofRepoId(notification.getRecipientUserId());
    final UserNotificationsQueue notificationsQueue = getNotificationsQueueOrNull(recipientUserId);
    if (notificationsQueue == null) {
        logger.trace("No notification queue was found for recipientUserId={}", recipientUserId);
        return;
    }
    notificationsQueue.addNotification(notification);
}


public int getNotificationsUnreadCount(UserId adUserId){
    return getNotificationsQueue(adUserId).getUnreadCount();
}


public UserNotificationsQueue getNotificationsQueue(UserId adUserId){
    final UserNotificationsQueue notificationsQueue = getNotificationsQueueOrNull(adUserId);
    if (notificationsQueue == null) {
        throw new IllegalArgumentException("No notifications queue found for AD_User_ID=" + adUserId);
    }
    return notificationsQueue;
}


public void enableForSession(String sessionId,UserId adUserId,JSONOptions jsonOptions){
    logger.trace("Enabling for sessionId={}, adUserId={}, jsonOptions={}", sessionId, adUserId, jsonOptions);
    final UserNotificationsQueue notificationsQueue = adUserId2notifications.computeIfAbsent(adUserId, k -> UserNotificationsQueue.builder().userId(adUserId).jsonOptions(jsonOptions).notificationsRepo(Services.get(INotificationRepository.class)).websocketSender(websocketSender).build());
    notificationsQueue.addActiveSessionId(sessionId);
    subscribeToEventTopicsIfNeeded();
}


@EventListener
public void onUserLanguageChanged(LanguagedChangedEvent event){
    final UserNotificationsQueue notificationsQueue = adUserId2notifications.get(event.getAdUserId());
    if (notificationsQueue != null) {
        notificationsQueue.setLanguage(event.getAdLanguage());
    }
}


public void disableForSession(String sessionId){
// TODO: implement
}


public UserNotificationsQueue getNotificationsQueueOrNull(UserId adUserId){
    return adUserId2notifications.get(adUserId);
}


}