package org.live.websocket.chat;
 public interface OnChatListener {


public void onAnchorOpenChatRoom(String chatRoomNum)
;

public void onRelieveShutupUserOnChatRoom(String chatRoomNum,String userAccount)
;

public void onShutupUserOnChatRoom(String chatRoomNum,String userAccount)
;

public void onAnchorDissolveChatRoom(String chatRoomNum)
;

public void onUserAttentionChatRoom(String userAccount,String chatRoomNum)
;

public void onRelieveUserAttentionChatRoom(String userAccount,String chatRoomNum)
;

public void onKickoutUserOnChatRoom(String chatRoomNum,String userAccount)
;

public void onRelieveKickoutUserOnChatRoom(String chatRoomNum,String userAccount)
;

}