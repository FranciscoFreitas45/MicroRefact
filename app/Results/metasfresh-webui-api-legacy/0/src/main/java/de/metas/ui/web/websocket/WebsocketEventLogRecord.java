package de.metas.ui.web.websocket;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@lombok.Value
public class WebsocketEventLogRecord {

 private  String destination;

 private  Object payload;


public boolean isDestinationMatching(String destinationFilter){
    if (destinationFilter == null || destinationFilter.isEmpty()) {
        return true;
    }
    return destination.toLowerCase().contains(destinationFilter.toLowerCase().trim());
}


}