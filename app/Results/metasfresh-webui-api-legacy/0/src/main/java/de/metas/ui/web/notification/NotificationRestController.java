package de.metas.ui.web.notification;
 import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.base.Splitter;
import de.metas.notification.UserNotificationsList;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.notification.json.JSONNotificationsList;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.user.UserId;
import io.swagger.annotations.Api;
@Api
@RestController
@RequestMapping(value = NotificationRestController.ENDPOINT)
public class NotificationRestController {

 public  String ENDPOINT;

@Autowired
 private  UserSession userSession;

@Autowired
 private  UserNotificationsService userNotificationsService;


@GetMapping("/all")
public JSONNotificationsList getNotifications(int limit){
    userSession.assertLoggedIn();
    final UserId adUserId = userSession.getLoggedUserId();
    final UserNotificationsList notifications = userNotificationsService.getNotifications(adUserId, limit);
    final JSONOptions jsonOpts = JSONOptions.of(userSession);
    return JSONNotificationsList.of(notifications, jsonOpts);
}


@PutMapping("/all/read")
public void markAllAsRead(){
    userSession.assertLoggedIn();
    final UserId adUserId = userSession.getLoggedUserId();
    userNotificationsService.markAllNotificationsAsRead(adUserId);
}


@GetMapping("/websocketEndpoint")
public String getWebsocketEndpoint(){
    userSession.assertLoggedIn();
    final UserId adUserId = userSession.getLoggedUserId();
    return userNotificationsService.getWebsocketEndpoint(adUserId);
}


@DeleteMapping("/{notificationId}")
public void deleteById(String notificationId){
    userSession.assertLoggedIn();
    final UserId adUserId = userSession.getLoggedUserId();
    userNotificationsService.deleteNotification(adUserId, notificationId);
}


@DeleteMapping("/all")
public void deleteAll(){
    userSession.assertLoggedIn();
    final UserId adUserId = userSession.getLoggedUserId();
    userNotificationsService.deleteAllNotification(adUserId);
}


@GetMapping("/unreadCount")
public int getNotificationsUnreadCount(){
    userSession.assertLoggedIn();
    final UserId adUserId = userSession.getLoggedUserId();
    return userNotificationsService.getNotificationsUnreadCount(adUserId);
}


@DeleteMapping
public void deleteByIds(String notificationIdsListStr){
    userSession.assertLoggedIn();
    final UserId adUserId = userSession.getLoggedUserId();
    final List<String> notificationIds = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(notificationIdsListStr);
    if (notificationIds.isEmpty()) {
        throw new AdempiereException("No IDs provided");
    }
    notificationIds.forEach(notificationId -> userNotificationsService.deleteNotification(adUserId, notificationId));
}


@PutMapping("/{notificationId}/read")
public void markAsRead(String notificationId){
    userSession.assertLoggedIn();
    final UserId adUserId = userSession.getLoggedUserId();
    userNotificationsService.markNotificationAsRead(adUserId, notificationId);
}


}