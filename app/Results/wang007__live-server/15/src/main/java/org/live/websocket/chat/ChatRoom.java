package org.live.websocket.chat;
 import org.live.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
public class ChatRoom {

 private  Logger LOGGER;

 private  String chatRoomNum;

 private  String anchorAccount;

 private  AtomicInteger onlineCount;

 private  boolean chatFlag;

 private  ConcurrentHashMap<String,WebSocketSession> onlineUserSessions;

/**
 * @param chatRoomNum   直播间号
 * @param anchorAccount 主播号
 */
public ChatRoom(String chatRoomNum, String anchorAccount, WebSocketSession session) {
    this.chatRoomNum = chatRoomNum;
    this.anchorAccount = anchorAccount;
    chatFlag = true;
    onlineCount = new AtomicInteger(1);
    onlineUserSessions = new ConcurrentHashMap<>();
    onlineUserSessions.put(anchorAccount, session);
}/**
 *  此时，直播间号 == 主播号
 * @param chatRoomNum 直播间号
 */
public ChatRoom(String chatRoomNum, WebSocketSession session) {
    this(chatRoomNum, chatRoomNum, session);
}
public void closeChatRoom(){
    this.chatFlag = false;
}


public boolean addUserSession(WebSocketSession session){
    if (!chatFlag)
        return false;
    // 获取用户的账号
    String usernameNum = (String) session.getAttributes().get(ChatConstants.USER_ACCOUNT_IN_WEBSOCKET_SESSION_KEY);
    onlineUserSessions.put(usernameNum, session);
    onlineCount.incrementAndGet();
    return true;
}


public void sendMessageToUser(Message message){
    // 某用户的账号
    String usernameNum = message.getDestination();
    WebSocketSession session = onlineUserSessions.get(usernameNum);
    if (session == null)
        return;
    TextMessage springTextMessage = new TextMessage(JsonUtils.toJson(message));
    try {
        if (session.isOpen()) {
            session.sendMessage(springTextMessage);
        } else {
            onlineUserSessions.remove(usernameNum);
            onlineCount.decrementAndGet();
        }
    } catch (IOException e) {
        LOGGER.error(e.getMessage(), e);
    }
}


public String getanchorAccount(){
    return anchorAccount;
}


public int getOnlineCount(){
    int localCount = onlineCount.intValue();
    if (localCount <= 1) {
        // 如果小于1，那就设置回去为1 ;
        onlineCount.set(1);
    }
    return onlineCount.intValue();
}


public String getChatRoomNum(){
    return chatRoomNum;
}


public WebSocketSession getSessionByAccount(String account){
    return onlineUserSessions.get(account);
}


public int removeUserSession(WebSocketSession session){
    // 获取用户的账号
    String usernameNum = (String) session.getAttributes().get(ChatConstants.USER_ACCOUNT_IN_WEBSOCKET_SESSION_KEY);
    onlineUserSessions.remove(usernameNum);
    return onlineCount.decrementAndGet();
}


public void sendMessageToCurrentChatRoom(Message message){
    TextMessage springTextMessage = new TextMessage(JsonUtils.toJson(message));
    Set<Map.Entry<String, WebSocketSession>> entryWebSessions = onlineUserSessions.entrySet();
    Iterator<Map.Entry<String, WebSocketSession>> iterator = entryWebSessions.iterator();
    try {
        while (iterator.hasNext()) {
            WebSocketSession session = iterator.next().getValue();
            if (session.isOpen()) {
                // session是否打开
                session.sendMessage(springTextMessage);
            } else {
                // 清除当前的session
                iterator.remove();
                onlineCount.decrementAndGet();
            }
        }
    } catch (IOException e) {
        LOGGER.error(e.getMessage(), e);
    }
}


public boolean getChatroomFlag(){
    return chatFlag;
}


public void dissolveChatRoom(Message message){
    TextMessage springTextMessage = new TextMessage(JsonUtils.toJson(message));
    this.closeChatRoom();
    Collection<WebSocketSession> sessions = onlineUserSessions.values();
    try {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                // 此时用户被动的离开，存一个标志，用于在关闭session的时候（afterConnectionClosed方法），就不用在进入直播间进行处理
                // 
                session.getAttributes().put(ChatConstants.USER_PASSIVE_EXIT_FLAG_WEBSOCKET_SESSION_KEY, ChatConstants.FLAG_DELEGATE_VALUE);
                // websocketSession还打开的就发送信息，关闭的就不用管了。
                session.sendMessage(springTextMessage);
                session.close();
            }
        }
    } catch (IOException e) {
        LOGGER.error(e.getMessage(), e);
    }
    // 清空chatRoom的session
    onlineUserSessions.clear();
    onlineUserSessions = null;
}


public ChatRoom resetChatRoom(WebSocketSession session){
    Map<String, Object> map = session.getAttributes();
    String chatroomNum = (String) map.get(ChatConstants.CHATROOM_NUMBER_IN_WEBSOCKET_SESSION_KEY);
    String anchorAccount = (String) map.get(ChatConstants.USER_ACCOUNT_IN_WEBSOCKET_SESSION_KEY);
    return this.resetChatRoom(chatroomNum, anchorAccount, session);
}


}