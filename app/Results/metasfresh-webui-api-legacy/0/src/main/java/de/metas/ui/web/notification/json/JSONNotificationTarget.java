package de.metas.ui.web.notification.json;
 import java.io.Serializable;
import org.slf4j.Logger;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.logging.LogManager;
import de.metas.notification.UserNotification;
import de.metas.notification.UserNotificationTargetType;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONNotificationTarget implements Serializable{

 private  Logger logger;

@JsonProperty("targetType")
 private  UserNotificationTargetType targetType;

@JsonProperty("windowId")
@JsonInclude(JsonInclude.Include.NON_ABSENT)
 private  String windowId;

@JsonProperty("documentType")
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@Deprecated
 private  String documentType;

@JsonProperty("documentId")
@JsonInclude(JsonInclude.Include.NON_ABSENT)
 private  String documentId;

@JsonProperty("viewId")
@JsonInclude(JsonInclude.Include.NON_ABSENT)
 private  String viewId;


public JSONNotificationTarget of(UserNotification notification){
    final UserNotificationTargetType targetType = notification.getTargetType();
    switch(targetType) {
        case Window:
            return JSONNotificationTarget.builder().targetType(UserNotificationTargetType.Window).windowId(notification.getTargetDocumentType()).documentId(notification.getTargetDocumentId()).build();
        case View:
            return JSONNotificationTarget.builder().targetType(UserNotificationTargetType.View).windowId(notification.getTargetDocumentType()).viewId(notification.getTargetViewId()).build();
        case None:
            return null;
        default:
            logger.warn("Unknown targetType={} for {}. Returning null.", targetType, notification);
            return null;
    }
}


}