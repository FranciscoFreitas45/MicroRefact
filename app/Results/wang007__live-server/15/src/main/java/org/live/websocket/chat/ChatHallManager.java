package org.live.websocket.chat;
 import org.live.common.utils.JsonUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.util.Map;
public class ChatHallManager {

 private  ChatHall chatHall;

 private  OnChatListener listener;

private ChatHallManager() {
}
public void exitChatRoom(WebSocketSession session){
    if (chatHall.removeWebSocketSessionToChatRoom(session)) {
        // 离开的是主播
        String chatroomNum = (String) session.getAttributes().get(ChatConstants.CHATROOM_NUMBER_IN_WEBSOCKET_SESSION_KEY);
        // 通知调用者。
        if (listener != null)
            listener.onAnchorDissolveChatRoom(chatroomNum);
    }
}


public String[] resolveDestination(String destination){
    if (destination == null || "".equals(destination)) {
        return new String[] { "", "" };
    }
    String[] destinations = destination.split("-");
    return destinations;
}


public Map<String,Integer> listChatroomOnlineCount(){
    return chatHall.listChatRoomOnlineCount();
}


public void dispatchMessage(WebSocketSession session,TextMessage textMessage){
    Message message = JsonUtils.fromJson(textMessage.getPayload(), Message.class);
    // 消息发往的目的地
    String destination = message.getDestination();
    switch(message.getMessageType()) {
        case MessageType.SEND_TO_USER_MESSAGE_TYPE:
            {
                // 发消息给某个用户
                String[] destinations = resolveDestination(destination);
                // 消息目的地是用户，
                message.setDestination(destinations[1]);
                chatHall.dispatchMessageToChatRoom(destinations[0], message);
                break;
            }
        case MessageType.SHUTUP_USER_MESSAGE_TYPE:
            {
                // 禁言用户
                String[] destinations = resolveDestination(destination);
                // 设置发往的直播间
                message.setDestination(destinations[0]);
                // 用户账号暂存到content中， 在chatHall获取用户账号，并重新设置message的content
                message.setContent(destinations[1]);
                // 通知调用者。 存库
                if (listener != null)
                    listener.onShutupUserOnChatRoom(destinations[0], destinations[1]);
                chatHall.dispatchMessageToChatRoom(destinations[0], message);
                break;
            }
        case MessageType.RELIEVE_SHUTUP_USER_MESSAGE_TYPE:
            {
                // 解除禁言
                String[] destinations = resolveDestination(destination);
                // 设置发往的直播间
                message.setDestination(destinations[0]);
                // 用户账号暂存到content中， 在chatHall获取用户账号，并重新设置message的content
                message.setContent(destinations[1]);
                // 通知调用者。 存库
                if (listener != null)
                    listener.onRelieveShutupUserOnChatRoom(destinations[0], destinations[1]);
                chatHall.dispatchMessageToChatRoom(destinations[0], message);
                break;
            }
        case MessageType.KICKOUT_USER_MESSAGE_TYPE:
            {
                // 踢出用户
                String[] destinations = resolveDestination(destination);
                // 设置发往的直播间
                message.setDestination(destinations[0]);
                // 用户账号暂存到content中， 在chatHall获取用户账号，并重新设置message的content
                message.setContent(destinations[1]);
                // 通知调用者。 存库
                if (listener != null)
                    listener.onKickoutUserOnChatRoom(destinations[0], destinations[1]);
                chatHall.dispatchMessageToChatRoom(destinations[0], message);
                break;
            }
        case MessageType.RELIEVE_KICKOUT_USER_MESSAGE_TYPE:
            {
                // 解除踢出
                String[] destinations = resolveDestination(destination);
                // 设置发往的直播间
                message.setDestination(destinations[0]);
                // 用户账号暂存到content中， 在chatHall获取用户账号，并重新设置message的content
                message.setContent(destinations[1]);
                // 通知调用者。 存库
                if (listener != null)
                    listener.onRelieveKickoutUserOnChatRoom(destinations[0], destinations[1]);
                chatHall.dispatchMessageToChatRoom(destinations[0], message);
                break;
            }
        case MessageType.USER_ATTENTION_CHATROOM:
            {
                // 用户关注直播间
                String[] destinations = resolveDestination(destination);
                // 设置发往的直播间
                message.setDestination(destinations[0]);
                // 用户账号暂存到content中， 在chatHall获取用户账号，并重新设置message的content
                message.setContent(destinations[1]);
                if (listener != null)
                    listener.onUserAttentionChatRoom(destinations[0], destinations[1]);
                chatHall.dispatchMessageToChatRoom(destinations[0], message);
                break;
            }
        case MessageType.RELIEVE_USER_ATTENTION_CHATROOM:
            {
                // 用户解除关注直播间
                String[] destinations = resolveDestination(destination);
                // 设置发往的直播间
                message.setDestination(destinations[0]);
                // 用户账号暂存到content中， 在chatHall获取用户账号，并重新设置message的content
                message.setContent(destinations[1]);
                if (listener != null)
                    listener.onRelieveUserAttentionChatRoom(destinations[0], destinations[1]);
                chatHall.dispatchMessageToChatRoom(destinations[0], message);
                break;
            }
        // 其他消息
        default:
            chatHall.dispatchMessageToChatRoom(destination, message);
    }
}


public void setupListener(OnChatListener listener){
    if (ChatHallManager.listener != null)
        throw new IllegalStateException("已经设置了监听器，不要重复设置");
    ChatHallManager.listener = listener;
}


public void enterChatRoom(WebSocketSession session){
    Map<String, Object> map = session.getAttributes();
    // 用户名
    String userAccount = (String) map.get(ChatConstants.USER_ACCOUNT_IN_WEBSOCKET_SESSION_KEY);
    // 直播间号
    String chatRoomNum = (String) map.get(ChatConstants.CHATROOM_NUMBER_IN_WEBSOCKET_SESSION_KEY);
    // chatHall.getChatRoom(chatRoomNum) ;
    if (map.get(ChatConstants.ANCHOR_FLAG_IN_WEBSOCKET_SESSION_KEY) != null) {
        // 主播开启直播间
        chatHall.generateChatRoom(chatRoomNum, userAccount, session);
        // 通知调用者，主播开启直播间。
        if (listener != null)
            listener.onAnchorOpenChatRoom(chatRoomNum);
    } else {
        // 观众进入直播间
        chatHall.addWebSocketSessionToChatRoom(session);
    }
}


public void dissolveChatRoom(String chatRoomNum){
    ChatRoom chatRoom = chatHall.getChatRoom(chatRoomNum);
    if (chatRoom != null)
        chatHall.dissolveChatRoom(chatRoom);
}


}