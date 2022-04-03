package de.metas.ui.web.notification.json;
 import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@ToString
public class JSONNotificationEvent implements Serializable{

@JsonProperty("eventType")
 private  EventType eventType;

@JsonProperty("notificationId")
 private  String notificationId;

@JsonProperty("notification")
@JsonInclude(JsonInclude.Include.NON_ABSENT)
 private  JSONNotification notification;

@JsonProperty("unreadCount")
@JsonInclude(JsonInclude.Include.NON_ABSENT)
 private  Integer unreadCount;


public JSONNotificationEvent eventDeleted(String notificationId,int unreadCount){
    final JSONNotification notification = null;
    return new JSONNotificationEvent(EventType.Delete, notificationId, notification, unreadCount);
}


public JSONNotificationEvent eventNew(JSONNotification notification,int unreadCount){
    String notificationId = notification.getId();
    return new JSONNotificationEvent(EventType.New, notificationId, notification, unreadCount);
}


public JSONNotificationEvent eventReadAll(){
    final String notificationId = null;
    final JSONNotification notification = null;
    final int unreadCount = 0;
    return new JSONNotificationEvent(EventType.ReadAll, notificationId, notification, unreadCount);
}


public JSONNotificationEvent eventDeletedAll(){
    final String notificationId = null;
    final JSONNotification notification = null;
    final int unreadCount = 0;
    return new JSONNotificationEvent(EventType.DeleteAll, notificationId, notification, unreadCount);
}


public JSONNotificationEvent eventRead(String notificationId,int unreadCount){
    final JSONNotification notification = null;
    return new JSONNotificationEvent(EventType.Read, notificationId, notification, unreadCount);
}


}