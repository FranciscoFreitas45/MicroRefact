package org.gliderwiki.web.user.dao;
 import java.util.List;
import org.gliderwiki.web.vo.AddFriendVo;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
@Repository("userConnectionDao")
public class UserConnectionDaoImpl extends SqlSessionDaoSupportimplements UserConnectionDao{


@Override
public List<AddFriendVo> getMyConnection(MemberSessionVo memberSessionVo){
    List<AddFriendVo> addFriendVoList = null;
    addFriendVoList = (List<AddFriendVo>) getSqlSession().selectList("MemberManage.getMyConnection", memberSessionVo);
    return addFriendVoList;
}


@Override
public List<AddFriendVo> getConnectionToMe(MemberSessionVo memberSessionVo){
    List<AddFriendVo> addFriendVoList = null;
    addFriendVoList = (List<AddFriendVo>) getSqlSession().selectList("MemberManage.getConnectionToMe", memberSessionVo);
    return addFriendVoList;
}


}