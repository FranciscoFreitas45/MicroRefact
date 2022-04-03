package de.metas.ui.web.devices;
 import org.springframework.stereotype.Component;
import de.metas.device.adempiere.AttributesDevicesHub.AttributeDeviceAccessor;
import de.metas.device.adempiere.IDevicesHubFactory;
import de.metas.ui.web.websocket.WebSocketConfig;
import de.metas.ui.web.websocket.WebSocketProducer;
import de.metas.ui.web.websocket.WebSocketProducerFactory;
import de.metas.ui.web.window.datatypes.Values;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
import lombok.ToString;
@Component
public class DeviceWebSocketProducerFactory implements WebSocketProducerFactory{

 private  String TOPICNAME_Prefix;

 private  String deviceId;


@Override
public String getTopicNamePrefix(){
    return TOPICNAME_Prefix;
}


public String buildDeviceTopicName(String deviceId){
    Check.assumeNotEmpty(deviceId, "deviceId is not empty");
    return TOPICNAME_Prefix + deviceId;
}


@Override
public WebSocketProducer createProducer(String topicName){
    final String deviceId = extractDeviceIdFromTopicName(topicName);
    return new DeviceWebSocketProducer(deviceId);
}


@Override
public Object produceEvent(JSONOptions jsonOpts){
    final AttributeDeviceAccessor deviceAccessor = Services.get(IDevicesHubFactory.class).getDefaultAttributesDevicesHub().getAttributeDeviceAccessorById(deviceId);
    if (deviceAccessor == null) {
        throw new RuntimeException("Device accessor no longer exists for: " + deviceId);
    }
    final Object valueObj = deviceAccessor.acquireValue();
    final Object valueJson = Values.valueToJsonObject(valueObj, jsonOpts);
    return JSONDeviceValueChangedEvent.of(deviceId, valueJson);
}


public String extractDeviceIdFromTopicName(String topicName){
    if (topicName == null) {
        return null;
    } else if (topicName.startsWith(TOPICNAME_Prefix)) {
        return topicName.substring(TOPICNAME_Prefix.length());
    } else {
        return null;
    }
}


}