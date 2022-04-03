package de.metas.ui.web.devices;
 import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONDeviceValueChangedEvent implements Serializable{

@JsonProperty("deviceId")
 private  String deviceId;

@JsonProperty("value")
 private  Object value;

@JsonProperty("timestampMillis")
 private  long timestampMillis;


public String getDeviceId(){
    return deviceId;
}


public Object getValue(){
    return value;
}


public long getTimestampMillis(){
    return timestampMillis;
}


public JSONDeviceValueChangedEvent of(String deviceId,Object jsonValue){
    final long timestampMillis = System.currentTimeMillis();
    return new JSONDeviceValueChangedEvent(deviceId, jsonValue, timestampMillis);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("deviceId", deviceId).add("value", value).add("timestampMillis", timestampMillis).toString();
}


}