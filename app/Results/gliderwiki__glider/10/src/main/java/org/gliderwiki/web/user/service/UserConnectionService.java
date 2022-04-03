package org.gliderwiki.web.user.service;
 import java.util.List;
import org.gliderwiki.web.vo.AddFriendVo;
import org.gliderwiki.web.vo.MemberSessionVo;
public interface UserConnectionService {


public List<AddFriendVo> getMyConnection(MemberSessionVo memberSessionVo)
;

public int addWeFriends(String arrayCheckId,Integer weUserIdx)
;

public List<AddFriendVo> getConnectionToMe(Integer weUserIdx)
;

}