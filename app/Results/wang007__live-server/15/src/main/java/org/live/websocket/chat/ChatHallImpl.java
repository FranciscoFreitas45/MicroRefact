package org.live.websocket.chat;
 import org.live.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class ChatHallImpl implements ChatHall{

 private  Logger LOGGER;

 private  ConcurrentHashMap<String,ChatRoom> chatRoomMap;

 private  int BUFFER_SIZE;

public ChatHallImpl() {
    chatRoomMap = new ConcurrentHashMap<>();
}
@Override
public void addWebSocketSessionToChatRoom(WebSocketSession session){
    Map<String, Object> attributes = session.getAttributes();
    // 获取直播间号
    String chatRoomNum = (String) attributes.get(ChatConstants.CHATROOM_NUMBER_IN_WEBSOCKET_SESSION_KEY);
    ChatRoom chatRoom = getChatRoom(chatRoomNum);
    Message message = new Message();
    message.setFromChatRoomNum(chatRoomNum);
    message.setDestination(chatRoomNum);
    // 1. 不存在直播间，此时直播大厅还未管理当前WebSocketSession，可以直接发消息关闭连接
    // 2. 此时聊天室关闭，
    if (chatRoom == null || !chatRoom.getChatroomFlag()) {
        // 信息事件
        message.setMessageType(MessageType.ANCHOR_EXIT_CHATROOM_MESSAGE_TYPE);
        TextMessage springTextMessage = new TextMessage(JsonUtils.toJson(message));
        // 用户进入直播间失败，存入标志，用于移除websocketSession的时候判断
        session.getAttributes().put(ChatConstants.USER_ENTER_FAIL_WEBSOCKET_SESSION_KEY, ChatConstants.FLAG_DELEGATE_VALUE);
        try {
            session.sendMessage(springTextMessage);
            // 关闭连接
            session.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return;
    }
    // 账号
    String account = (String) attributes.get(ChatConstants.USER_ACCOUNT_IN_WEBSOCKET_SESSION_KEY);
    // 昵称
    String nickname = (String) attributes.get(ChatConstants.NICKNAME_IN_WEBSOCKET_SESSION_KEY);
    chatRoom.addUserSession(session);
    // 在线用户数
    int onlineCount = chatRoom.getOnlineCount();
    // 在线人数用户放到消息的内容上
    message.setContent(onlineCount + "");
    // 消息类型
    message.setMessageType(MessageType.USER_ENTER_CHATROOM_MESSAGE_TYPE);
    message.setAccount(account);
    message.setNickname(nickname);
    chatRoom.sendMessageToCurrentChatRoom(message);
}


@Override
public void dispatchMessageToChatRoom(String chatRoomNum,Message message){
    // 消息事件类型
    int messageType = message.getMessageType();
    // 获取直播间
    ChatRoom chatRoom = getChatRoom(chatRoomNum);
    if (chatRoom == null)
        return;
    switch(messageType) {
        case MessageType.SEND_TO_CHATROOM_MESSAGE_TYPE:
            {
                // 发送信息至直播间
                chatRoom.sendMessageToCurrentChatRoom(message);
                break;
            }
        // 先发消息给这个用户，设置他不能发言。 然后再把这件事广播给直播间的所有用户
        case MessageType.SHUTUP_USER_MESSAGE_TYPE:
            {
                // 主播禁言用户
                String userAccount = message.getContent();
                WebSocketSession session = chatRoom.getSessionByAccount(userAccount);
                if (session != null) {
                    // 先禁言该用户，
                    Message specialMessage = new Message();
                    specialMessage.setAccount(ChatConstants.SYSTEM_NUM);
                    specialMessage.setNickname(ChatConstants.SYSTEM_NAME);
                    specialMessage.setMessageType(MessageType.SHUTUP_USER_MESSAGE_TYPE);
                    specialMessage.setDestination(userAccount);
                    chatRoom.sendMessageToUser(specialMessage);
                }
                // 再把禁言这事，广播到这个直播间。系统消息
                // 获取昵称
                // String nickname  = (String) session.getAttributes().get(ChatConstants.NICKNAME_IN_WEBSOCKET_SESSION_KEY) ;
                String nickname = message.getNickname();
                Message broadcastMessage = new Message();
                broadcastMessage.setFromChatRoomNum(chatRoomNum);
                broadcastMessage.setDestination(chatRoomNum);
                // 用户账号
                broadcastMessage.setAccount(userAccount);
                broadcastMessage.setNickname(nickname);
                broadcastMessage.setContent(nickname + " 被禁言");
                broadcastMessage.setMessageType(MessageType.SYSTEM_MESSAGE_TYPE);
                // 广播到直播间，
                chatRoom.sendMessageToCurrentChatRoom(broadcastMessage);
                break;
            }
        // 解除禁言，如果用户在线就告诉他。 不用广播到直播间中
        case MessageType.RELIEVE_SHUTUP_USER_MESSAGE_TYPE:
            {
                // 主播解除禁言
                // 用户的账号
                String userAccount = message.getContent();
                WebSocketSession session = chatRoom.getSessionByAccount(userAccount);
                if (session != null) {
                    // 获取昵称
                    // String nickname  = (String) session.getAttributes().get(ChatConstants.NICKNAME_IN_WEBSOCKET_SESSION_KEY);
                    // 消息目的地是 用户
                    message.setDestination(userAccount);
                    message.setContent(message.getNickname() + "被解除禁言");
                    chatRoom.sendMessageToUser(message);
                }
                // 获取主播的账号
                String anchorAccount = chatRoom.getanchorAccount();
                WebSocketSession anchorSession = chatRoom.getSessionByAccount(anchorAccount);
                if (anchorSession != null) {
                    // 发消息给主播
                    Message anchorMessage = new Message();
                    message.setFromChatRoomNum(chatRoomNum);
                    // 系统消息的标记
                    message.setAccount(userAccount);
                    // 被解除用户的昵称
                    message.setNickname(message.getNickname());
                    message.setContent(message.getNickname() + " 被解除禁言");
                    // 消息目的地是主播一人
                    message.setDestination(anchorAccount);
                    message.setMessageType(MessageType.SYSTEM_MESSAGE_TYPE);
                    chatRoom.sendMessageToUser(anchorMessage);
                }
                break;
            }
        case MessageType.SEND_TO_USER_MESSAGE_TYPE:
            {
                // 发送信息至直播间的某个用户
                chatRoom.sendMessageToUser(message);
                break;
            }
        case MessageType.ANCHOR_EXIT_CHATROOM_MESSAGE_TYPE:
            {
                // 主播离开，直播结束
                dissolveChatRoom(chatRoom, message);
                break;
            }
        case MessageType.USER_ENTER_CHATROOM_MESSAGE_TYPE:
            {
            // 有用户进入直播间
            // chatRoom.sendMessageToCurrentChatRoom(message) ;
            // break ;
            }
        case MessageType.USER_EXIT_CHATROOM_MESSAGE_TYPE:
            {
                // 有用户离开直播间
                chatRoom.sendMessageToCurrentChatRoom(message);
                break;
            }
        // 先踢出这个用户，再把这事广播到直播间中
        case MessageType.KICKOUT_USER_MESSAGE_TYPE:
            {
                // 主播踢出用户
                String userAccount = message.getContent();
                WebSocketSession session = chatRoom.getSessionByAccount(userAccount);
                // 踢出用户
                if (session != null) {
                    Message specialMessage = new Message();
                    specialMessage.setAccount(ChatConstants.SYSTEM_NUM);
                    specialMessage.setNickname(ChatConstants.SYSTEM_NAME);
                    specialMessage.setDestination(userAccount);
                    specialMessage.setMessageType(MessageType.KICKOUT_USER_MESSAGE_TYPE);
                    // 发消息给这个用户
                    chatRoom.sendMessageToUser(specialMessage);
                    // 把这个session从chatRoom中移除， 先存标记，afterConnectionClosed方法中判断要不要做处理。
                    session.getAttributes().put(ChatConstants.USER_PASSIVE_EXIT_FLAG_WEBSOCKET_SESSION_KEY, ChatConstants.FLAG_DELEGATE_VALUE);
                    removeWebSocketSessionToChatRoom(session);
                }
                // 广播踢出用户消息
                // 获取昵称
                // String nickname  = (String) session.getAttributes().get(ChatConstants.NICKNAME_IN_WEBSOCKET_SESSION_KEY);
                String nickname = message.getNickname();
                Message broadcastMessage = new Message();
                // 用户账号
                broadcastMessage.setAccount(userAccount);
                broadcastMessage.setNickname(nickname);
                broadcastMessage.setContent(nickname + " 被踢出直播间");
                broadcastMessage.setDestination(chatRoomNum);
                broadcastMessage.setMessageType(MessageType.SYSTEM_MESSAGE_TYPE);
                chatRoom.sendMessageToCurrentChatRoom(broadcastMessage);
                break;
            }
        // 解除踢出时，此时用户并不在直播间， 所以不用做任何处理
        case MessageType.RELIEVE_KICKOUT_USER_MESSAGE_TYPE:
            {
                // 主播解除用户踢出
                /*String userAccount = message.getContent() ;
                WebSocketSession session = chatRoom.getSessionByAccount(userAccount);
                if(session != null) {
                    //获取昵称
                    String nickname  = (String) session.getAttributes().get(ChatConstants.NICKNAME_IN_WEBSOCKET_SESSION_KEY);
                    message.setContent(nickname) ;
                    chatRoom.sendMessageToCurrentChatRoom(message) ;
                }*/
                // 把消息告诉给主播
                // 解除踢出的用户
                String userAccount = message.getContent();
                // 主播的账号
                String anchorAccount = chatRoom.getanchorAccount();
                WebSocketSession anchorSession = chatRoom.getSessionByAccount(anchorAccount);
                if (anchorSession != null) {
                    Message anchorMessage = new Message();
                    // 被解除踢出的用户账号
                    anchorMessage.setAccount(userAccount);
                    // //被解除踢出的用户昵称
                    anchorMessage.setNickname(message.getNickname());
                    // 系统消息
                    anchorMessage.setMessageType(MessageType.SYSTEM_MESSAGE_TYPE);
                    anchorMessage.setFromChatRoomNum(chatRoomNum);
                    // 消息目的地是主播
                    anchorMessage.setDestination(anchorAccount);
                    anchorMessage.setContent(message.getNickname() + " 被解除踢出");
                    chatRoom.sendMessageToUser(anchorMessage);
                }
                break;
            }
        case MessageType.USER_ATTENTION_CHATROOM:
            {
                // 用户关注直播间
                String userAccount = message.getContent();
                Message broadcastMessage = new Message();
                broadcastMessage.setDestination(chatRoomNum);
                broadcastMessage.setAccount(userAccount);
                broadcastMessage.setContent(message.getNickname() + " 关注了直播间");
                broadcastMessage.setNickname(message.getNickname());
                // 移动端接收消息是某用户关注了直播间
                broadcastMessage.setMessageType(MessageType.USER_ATTENTION_CHATROOM);
                chatRoom.sendMessageToCurrentChatRoom(broadcastMessage);
                break;
            }
        // 用户取消关注，消息只推送给本人
        case MessageType.RELIEVE_USER_ATTENTION_CHATROOM:
            {
                // 用户解除关注直播间
                String userAccount = message.getContent();
                Message specialMessage = new Message();
                // 消息目的地是本人
                specialMessage.setDestination(userAccount);
                specialMessage.setAccount(userAccount);
                specialMessage.setNickname(message.getNickname());
                specialMessage.setContent(message.getNickname() + " 取消关注");
                specialMessage.setMessageType(MessageType.RELIEVE_USER_ATTENTION_CHATROOM);
                // 把消息推送给本人。
                chatRoom.sendMessageToUser(specialMessage);
                break;
            }
    }
}


@Override
public boolean getChatRoomChatFlag(String chatroomNum){
    ChatRoom chatRoom = chatRoomMap.get(chatroomNum);
    return chatRoom == null ? false : chatRoom.getChatroomFlag();
}


@Override
public Map<String,Integer> listChatRoomOnlineCount(){
    int chatRoomCount = chatRoomMap.size();
    Map<String, Integer> map = new HashMap<>(chatRoomCount + BUFFER_SIZE);
    Collection<ChatRoom> chatRooms = chatRoomMap.values();
    for (ChatRoom chatRoom : chatRooms) {
        if (chatRoom == null)
            continue;
        map.put(chatRoom.getChatRoomNum(), chatRoom.getOnlineCount());
    }
    return map;
}


@Override
public ChatRoom generateChatRoom(String chatRoomNum,String anchorAccount,WebSocketSession session){
    if (chatRoomMap.containsKey(chatRoomNum)) {
        ChatRoom chatRoom = chatRoomMap.get(chatRoomNum);
        if (chatRoom != null)
            return chatRoom.resetChatRoom(session);
    }
    ChatRoom newChatRoom = new ChatRoom(chatRoomNum, anchorAccount, session);
    chatRoomMap.put(chatRoomNum, newChatRoom);
    return newChatRoom;
}


@Override
public boolean removeWebSocketSessionToChatRoom(WebSocketSession session){
    Map<String, Object> map = session.getAttributes();
    // 直播间号
    String chatroomNum = (String) map.get(ChatConstants.CHATROOM_NUMBER_IN_WEBSOCKET_SESSION_KEY);
    // 用户账号
    String userAccount = (String) map.get(ChatConstants.USER_ACCOUNT_IN_WEBSOCKET_SESSION_KEY);
    // 
    String nickname = (String) map.get(ChatConstants.NICKNAME_IN_WEBSOCKET_SESSION_KEY);
    // 主播标记
    Object anchorFlag = map.get(ChatConstants.ANCHOR_FLAG_IN_WEBSOCKET_SESSION_KEY);
    ChatRoom chatRoom = this.getChatRoom(chatroomNum);
    Message message = new Message();
    message.setFromChatRoomNum(chatroomNum);
    message.setDestination(chatroomNum);
    message.setNickname(nickname);
    message.setAccount(userAccount);
    // String anchorInChatroom = chatRoom.getanchorAccount() ; //直播间的主播账号
    if (anchorFlag != null) {
        // || userAccount.equals(anchorInChatroom)
        message.setMessageType(MessageType.ANCHOR_EXIT_CHATROOM_MESSAGE_TYPE);
        dispatchMessageToChatRoom(chatroomNum, message);
        return true;
    }
    message.setMessageType(MessageType.USER_EXIT_CHATROOM_MESSAGE_TYPE);
    // 先移除这个websocketSession
    chatRoom.removeUserSession(session);
    // 再统计人数
    message.setContent(chatRoom.getOnlineCount() + "");
    dispatchMessageToChatRoom(chatroomNum, message);
    return false;
}


@Override
public void dissolveChatRoom(ChatRoom chatRoom,Message message){
    chatRoom.dissolveChatRoom(message);
    // 从聊天大厅中删除这个直播间
    chatRoomMap.remove(chatRoom.getChatRoomNum());
}


@Override
public ChatRoom getChatRoom(String chatRoomNum){
    return chatRoomMap.get(chatRoomNum);
}


@Override
public int getChatRoomOnLineCount(String chatroomNum){
    return chatRoomMap.get(chatroomNum).getOnlineCount();
}


}