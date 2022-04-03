package org.live.websocket.chat;
 import org.springframework.web.socket.WebSocketSession;
import java.util.Map;
public interface ChatHall {


public void addWebSocketSessionToChatRoom(WebSocketSession session)
;

public void dispatchMessageToChatRoom(String chatRoomNum,Message message)
;

public boolean getChatRoomChatFlag(String chatroomNum)
;

public Map<String,Integer> listChatRoomOnlineCount()
;

public ChatRoom generateChatRoom(String chatRoomNum,String anchorAccount,WebSocketSession session)
;

public boolean removeWebSocketSessionToChatRoom(WebSocketSession session)
;

public void dissolveChatRoom(ChatRoom chatRoom,Message message)
;

public ChatRoom getChatRoom(String chatRoomNum)
;

public int getChatRoomOnLineCount(String chatroomNum)
;

}