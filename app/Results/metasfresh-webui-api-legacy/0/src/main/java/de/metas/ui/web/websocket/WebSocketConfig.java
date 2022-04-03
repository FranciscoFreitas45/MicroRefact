package de.metas.ui.web.websocket;
 import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;
import org.springframework.web.socket.server.HandshakeInterceptor;
import com.google.common.base.Preconditions;
import de.metas.logging.LogManager;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.user.UserId;
import de.metas.util.Check;
import lombok.NonNull;
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

 private  Logger logger;

 private  String ENDPOINT;

 private  String TOPIC_UserSession;

 private  String TOPIC_Notifications;

 private  String TOPIC_View;

 private  String TOPIC_Document;

 private  String TOPIC_Board;

 public  String TOPIC_Dashboard;

 public  String TOPIC_Devices;

 private  Logger logger;

 private  Logger logger;

@Autowired
 private  WebSocketProducersRegistry websocketProducersRegistry;

@Autowired
 private  WebSocketProducersRegistry websocketProducersRegistry;

@Autowired
 private  WebSocketProducersRegistry websocketProducersRegistry;


@Override
public void onApplicationEvent(SessionDisconnectEvent event){
    final String sessionId = event.getSessionId();
    websocketProducersRegistry.onSessionDisconnect(sessionId);
}


@Override
public boolean configureMessageConverters(List<MessageConverter> messageConverters){
    messageConverters.add(new MappingJackson2MessageConverter());
    return true;
}


public String extractSimpSessionId(AbstractSubProtocolEvent event){
    return extractSimpHeaderAsString(event, "simpSessionId");
}


@Override
public boolean beforeHandshake(ServerHttpRequest request,ServerHttpResponse response,WebSocketHandler wsHandler,Map<String,Object> attributes){
    // UserSession.getCurrent().assertLoggedIn();
    // return true;
    final UserSession userSession = UserSession.getCurrentOrNull();
    if (userSession == null) {
        logger.warn("Websocket connection not allowed (missing userSession)");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return false;
    }
    if (!userSession.isLoggedIn()) {
        logger.warn("Websocket connection not allowed (not logged in) - userSession={}", userSession);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return false;
    }
    return true;
}


public String buildUserSessionTopicName(UserId adUserId){
    return TOPIC_UserSession + "/" + adUserId.getRepoId();
}


public String buildNotificationsTopicName(UserId adUserId){
    return TOPIC_Notifications + "/" + adUserId.getRepoId();
}


@Override
public void configureMessageBroker(MessageBrokerRegistry config){
    // use the /topic prefix for outgoing WebSocket communication
    config.enableSimpleBroker(TOPIC_UserSession, TOPIC_Notifications, TOPIC_View, TOPIC_Document, TOPIC_Board, TOPIC_Dashboard, TOPIC_Devices);
    // use the /app prefix for others
    config.setApplicationDestinationPrefixes("/app");
}


@Override
public void configureClientOutboundChannel(ChannelRegistration registration){
    // 
    // IMPORTANT: make sure we are using only one thread for sending outbound messages.
    // If not, it might be that the messages will not be sent in the right order,
    // and that's important for things like WS notifications API.
    // ( thanks to http://stackoverflow.com/questions/29689838/sockjs-receive-stomp-messages-from-spring-websocket-out-of-order )
    registration.taskExecutor().corePoolSize(1).maxPoolSize(1);
}


@Override
public void afterHandshake(ServerHttpRequest request,ServerHttpResponse response,WebSocketHandler wsHandler,Exception exception){
// nothing
}


public String extractSimpDestination(AbstractSubProtocolEvent event){
    return extractSimpHeaderAsString(event, "simpDestination");
}


public String extractSimpHeaderAsString(AbstractSubProtocolEvent event,String name){
    final Object simpDestinationObj = event.getMessage().getHeaders().get(name);
    return simpDestinationObj == null ? null : simpDestinationObj.toString();
}


@Override
public void registerStompEndpoints(StompEndpointRegistry registry){
    // the endpoint for websocket connections
    registry.addEndpoint(ENDPOINT).setAllowedOrigins(// FIXME: for now we allow any origin
    "*").addInterceptors(new WebSocketHandshakeInterceptor()).withSockJS();
}


@Override
public void configureClientInboundChannel(ChannelRegistration registration){
    registration.setInterceptors(new WebSocketChannelInterceptor());
// NOTE: atm we don't care if the inbound messages arrived in the right order
// When and If we would care we would restrict the taskExecutor()'s corePoolSize to ONE.
// see: configureClientOutboundChannel().
}


public String buildBoardTopicName(int boardId){
    Preconditions.checkArgument(boardId > 0);
    return TOPIC_Board + "/" + boardId;
}


public String buildViewNotificationsTopicName(String viewId){
    Check.assumeNotEmpty(viewId, "viewId is not empty");
    return TOPIC_View + "/" + viewId;
}


public String buildDocumentTopicName(WindowId windowId,DocumentId documentId){
    return TOPIC_Document + "/" + windowId.toJson() + "/" + documentId.toJson();
}


@Override
public void afterSendCompletion(Message<?> message,MessageChannel channel,boolean sent,Exception ex){
    if (!sent) {
        logger.warn("Failed sending: message={}, channel={}, sent={}", message, channel, sent, ex);
    }
}


@Override
public void afterReceiveCompletion(Message<?> message,MessageChannel channel,Exception ex){
    if (ex != null) {
        logger.warn("Failed receiving: message={}, channel={}", message, channel, ex);
    }
}


}