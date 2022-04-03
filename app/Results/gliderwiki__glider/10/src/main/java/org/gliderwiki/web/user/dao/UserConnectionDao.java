package org.gliderwiki.web.user.dao;
 import java.util.List;
import org.gliderwiki.web.vo.AddFriendVo;
import org.gliderwiki.web.vo.MemberSessionVo;
public interface UserConnectionDao {


public List<AddFriendVo> getMyConnection(MemberSessionVo memberSessionVo)
;

public List<AddFriendVo> getConnectionToMe(MemberSessionVo memberSessionVo)
;

}