package de.metas.ui.web.notification;
 import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import de.metas.logging.LogManager;
import de.metas.notification.INotificationRepository;
import de.metas.notification.UserNotification;
import de.metas.notification.UserNotificationsList;
import de.metas.ui.web.notification.json.JSONNotification;
import de.metas.ui.web.notification.json.JSONNotificationEvent;
import de.metas.ui.web.websocket.WebSocketConfig;
import de.metas.ui.web.websocket.WebsocketSender;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.user.UserId;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
public class UserNotificationsQueue {

 private  Logger logger;

 private  UserId userId;

 private  JSONOptions jsonOptions;

 private  Set<String> activeSessions;

 private  INotificationRepository notificationsRepo;

 private  WebsocketSender websocketSender;

 private  String websocketEndpoint;


public void removeActiveSessionId(String sessionId){
    activeSessions.remove(sessionId);
    logger.debug("Removed sessionId '{}' to {}", sessionId, this);
}


public String getWebsocketEndpoint(){
    return websocketEndpoint;
}


public void deleteAll(){
    notificationsRepo.deleteAllByUserId(getUserId());
    fireEventOnWebsocket(JSONNotificationEvent.eventDeletedAll());
}


public void addActiveSessionId(String sessionId){
    Check.assumeNotNull(sessionId, "Parameter sessionId is not null");
    activeSessions.add(sessionId);
    logger.debug("Added sessionId '{}' to {}", sessionId, this);
}


public boolean hasActiveSessions(){
    return !activeSessions.isEmpty();
}


public int getUnreadCount(){
    return notificationsRepo.getUnreadCountByUserId(getUserId());
}


public void delete(String notificationId){
    notificationsRepo.deleteById(Integer.parseInt(notificationId));
    fireEventOnWebsocket(JSONNotificationEvent.eventDeleted(notificationId, getUnreadCount()));
}


public void markAsRead(String notificationId){
    notificationsRepo.markAsReadById(Integer.parseInt(notificationId));
    fireEventOnWebsocket(JSONNotificationEvent.eventRead(notificationId, getUnreadCount()));
}


public UserNotificationsList getNotificationsAsList(int limit){
    final List<UserNotification> notifications = notificationsRepo.getByUserId(userId, limit);
    final boolean fullyLoaded = limit <= 0 || notifications.size() <= limit;
    final int totalCount;
    final int unreadCount;
    if (fullyLoaded) {
        totalCount = notifications.size();
        unreadCount = (int) notifications.stream().filter(UserNotification::isNotRead).count();
    } else {
        totalCount = notificationsRepo.getTotalCountByUserId(userId);
        unreadCount = notificationsRepo.getUnreadCountByUserId(userId);
    }
    return UserNotificationsList.of(notifications, totalCount, unreadCount);
}


public void markAllAsRead(){
    logger.trace("Marking all notifications as read (if any) for {}...", this);
    notificationsRepo.markAllAsReadByUserId(getUserId());
    fireEventOnWebsocket(JSONNotificationEvent.eventReadAll());
}


public void addNotification(UserNotification notification){
    final UserId adUserId = getUserId();
    Check.assume(notification.getRecipientUserId() == adUserId.getRepoId(), "notification's recipient user ID shall be {}: {}", adUserId, notification);
    final JSONNotification jsonNotification = JSONNotification.of(notification, jsonOptions);
    fireEventOnWebsocket(JSONNotificationEvent.eventNew(jsonNotification, getUnreadCount()));
}


public void fireEventOnWebsocket(JSONNotificationEvent event){
    websocketSender.convertAndSend(websocketEndpoint, event);
    logger.trace("Fired notification to WS {}: {}", websocketEndpoint, event);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("websocketEndpoint", websocketEndpoint).toString();
}


public UserId getUserId(){
    return userId;
}


public void setLanguage(String adLanguage){
    this.jsonOptions = jsonOptions.withAdLanguage(adLanguage);
}


}