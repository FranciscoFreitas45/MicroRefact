package de.metas.ui.web.websocket;
 public interface WebSocketProducerFactory {


public String getTopicNamePrefix()
;

public WebSocketProducer createProducer(String topicName)
;

}