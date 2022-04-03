package org.live.websocket;
 import org.live.websocket.chat.ChatConstants;
import org.live.websocket.chat.ChatHallManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.Map;
public class ChatWebSocketHandler extends TextWebSocketHandler{

 private  Logger LOGGER;


@Override
public void handleTextMessage(WebSocketSession session,TextMessage message) throws Exception{
    try {
        ChatHallManager.dispatchMessage(session, message);
    } catch (Throwable e) {
        LOGGER.error("websocket->handleTextMessage,异常 ", e);
    }
}


@Override
public void afterConnectionEstablished(WebSocketSession session) throws Exception{
    try {
        ChatHallManager.enterChatRoom(session);
    } catch (Throwable e) {
        LOGGER.error("websocket->afterConnectionEstablished,异常 ", e);
    }
}


@Override
public void afterConnectionClosed(WebSocketSession session,CloseStatus status) throws Exception{
    try {
        Map<String, Object> map = session.getAttributes();
        // 1. 判断用户之前是否有成功进入到直播间
        // 2. 判断session的关闭操作是不是由于主播的关闭直播间造成的
        // 是的话，就不用进行处理了
        if (map.get(ChatConstants.USER_ENTER_FAIL_WEBSOCKET_SESSION_KEY) != null || map.get(ChatConstants.USER_PASSIVE_EXIT_FLAG_WEBSOCKET_SESSION_KEY) != null)
            return;
        ChatHallManager.exitChatRoom(session);
    } catch (Throwable e) {
        LOGGER.error("websocket->afterConnectionClosed,异常 ", e);
    }
}


}