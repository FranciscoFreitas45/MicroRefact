package org.gliderwiki.admin.dao;
 import java.util.List;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeGroupUser;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.GroupUserVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
@Repository("adminGroupDao")
public class AdminGroupDaoImpl extends SqlSessionDaoSupportimplements AdminGroupDao{


@Override
public List<GroupUserVo> getGroupUserList(GroupUserVo groupUser){
    List<GroupUserVo> userList = null;
    userList = (List<GroupUserVo>) getSqlSession().selectList("AdminManage.getGroupUserList", groupUser);
    return userList;
}


@Override
public int insertGroupInfo(WeGroupInfo weGroupInfo){
    return getSqlSession().update("AdminManage.insertGroupInfo", weGroupInfo);
}


@Override
public int deleteUserInGroupDWR(WeGroupUser groupUser){
    int result = getSqlSession().update("AdminManage.deleteUserInGroupDWR", groupUser);
    return result;
}


@Override
public int deleteGroupInfoDWR(WeGroupInfo domain){
    int result = getSqlSession().update("AdminManage.deleteGroupInfoDWR", domain);
    return result;
}


@Override
public List<WeUser> getSearchAdminList(){
    List<WeUser> userList = null;
    userList = (List<WeUser>) getSqlSession().selectList("AdminManage.getSearchAdminList");
    return userList;
}


@Override
public List<WeGroupInfo> getGroupInfoList(){
    List<WeGroupInfo> groupList = null;
    groupList = (List<WeGroupInfo>) getSqlSession().selectList("AdminManage.getGroupInfoList");
    return groupList;
}


@Override
public int insertGroupUser(WeGroupUser weGroupUser){
    return getSqlSession().update("AdminManage.insertGroupUser", weGroupUser);
}


@Override
public List<WeGroupInfo> getUserJoinGroupList(Integer weUserIdx){
    List<WeGroupInfo> groupList = getSqlSession().selectList("AdminManage.getUserJoinGroupList", weUserIdx);
    return groupList;
}


@Override
public int deleteGroupUserDWR(WeGroupUser domain){
    int result = getSqlSession().update("AdminManage.deleteGroupUserDWR", domain);
    return result;
}


}