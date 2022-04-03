package de.metas.ui.web.notification.json;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.notification.UserNotificationsList;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.GuavaCollectors;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONNotificationsList {

 public  JSONNotificationsList EMPTY;

@JsonProperty("totalCount")
 private  int totalCount;

@JsonProperty("unreadCount")
 private  int unreadCount;

@JsonProperty("notifications")
 private  List<JSONNotification> notifications;


public List<JSONNotification> getNotifications(){
    return notifications;
}


public JSONNotificationsList of(UserNotificationsList notifications,JSONOptions jsonOpts){
    if (notifications.isEmpty()) {
        return EMPTY;
    }
    return new JSONNotificationsList(notifications, jsonOpts);
}


public int getTotalCount(){
    return totalCount;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("totalCount", totalCount).add("unreadCount", unreadCount).add("notifications", notifications).toString();
}


public int getUnreadCount(){
    return unreadCount;
}


}