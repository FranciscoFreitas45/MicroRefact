package de.metas.ui.web.notification.json;
 import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import de.metas.notification.UserNotification;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONNotification implements Serializable{

@JsonProperty("id")
 private  String id;

@JsonProperty("message")
 private  String message;

@JsonProperty("timestamp")
 private  String timestamp;

@JsonProperty("important")
 private  boolean important;

@JsonProperty("read")
 private  boolean read;

@JsonProperty("target")
 private  JSONNotificationTarget target;


public String getTimestamp(){
    return timestamp;
}


public JSONNotification of(UserNotification notification,JSONOptions jsonOpts){
    return new JSONNotification(notification, jsonOpts);
}


public boolean isImportant(){
    return important;
}


public boolean isRead(){
    return read;
}


public String getMessage(){
    return message;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("id", id).add("message", message).add("timestamp", timestamp).add("important", important).add("read", read).add("target", target).toString();
}


public String getId(){
    return id;
}


}