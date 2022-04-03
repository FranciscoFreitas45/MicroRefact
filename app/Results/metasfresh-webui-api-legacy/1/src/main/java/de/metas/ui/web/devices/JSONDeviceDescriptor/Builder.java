package de.metas.ui.web.devices.JSONDeviceDescriptor;
 import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import de.metas.util.Check;
public class Builder {

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


public String getWebsocketEndpoint(){
    Check.assumeNotEmpty(_websocketEndpoint, "websocketEndpoint is not empty");
    return _websocketEndpoint;
}


public Builder setCaption(String caption){
    _caption = caption;
    return this;
}


public Builder setDeviceId(String deviceId){
    _deviceId = deviceId;
    return this;
}


public String getCaption(){
    return _caption;
}


public Builder setWebsocketEndpoint(String websocketEndpoint){
    _websocketEndpoint = websocketEndpoint;
    return this;
}


}