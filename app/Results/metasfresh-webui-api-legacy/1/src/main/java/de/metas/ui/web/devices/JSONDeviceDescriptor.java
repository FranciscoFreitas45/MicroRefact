package de.metas.ui.web.devices;
 import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import de.metas.util.Check;
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONDeviceDescriptor implements Serializable{

@JsonProperty("deviceId")
 private  String deviceId;

@JsonProperty("caption")
 private  String caption;

@JsonProperty("websocketEndpoint")
 private  String websocketEndpoint;

 private  String _deviceId;

 private  String _caption;

 private  String _websocketEndpoint;


public String getDeviceId(){
    Check.assumeNotEmpty(_deviceId, "deviceId is not empty");
    return _deviceId;
}


public JSONDeviceDescriptor build(){
    return new JSONDeviceDescriptor(this);
}


public Builder builder(){
    return new Builder();
}


public String getWebsocketEndpoint(){
    Check.assumeNotEmpty(_websocketEndpoint, "websocketEndpoint is not empty");
    return _websocketEndpoint;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("deviceId", deviceId).add("caption", caption).add("websocketEndpoint", websocketEndpoint).toString();
}


public String getId(){
    return deviceId;
}


public Builder setCaption(String caption){
    _caption = caption;
    return this;
}


public String getCaption(){
    return _caption;
}


public Builder setDeviceId(String deviceId){
    _deviceId = deviceId;
    return this;
}


public Builder setWebsocketEndpoint(String websocketEndpoint){
    _websocketEndpoint = websocketEndpoint;
    return this;
}


}