package org.gliderwiki.Interface;
public interface UserConnectionService {

   public List<AddFriendVo> getMyConnection(MemberSessionVo memberSessionVo);
   public List<AddFriendVo> getConnectionToMe(Integer weUserIdx);
   public int addWeFriends(String arrayCheckId,Integer weUserIdx);
}