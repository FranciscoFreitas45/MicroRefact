package org.gliderwiki.web.space.dao;
 import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.WeSpaceUser;
import org.gliderwiki.web.vo.GroupUserVo;
import org.gliderwiki.web.vo.SpaceInfoVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Maps;
@Repository("spaceDao")
public class SpaceDao extends SqlSessionDaoSupport{


@SuppressWarnings("unchecked")
public List<GroupUserVo> getGroupUserList(int groupIdx){
    List<GroupUserVo> groupUserList = null;
    groupUserList = getSqlSession().selectList("SpaceManage.getGroupUserList", groupIdx);
    return groupUserList;
}


@SuppressWarnings("unchecked")
public List<Map<String,Object>> getGroupParticipant(String tranType,int spaceIdx){
    List<Map<String, Object>> groupList = null;
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("spaceIdx", spaceIdx);
    mapper.put("tranType", tranType);
    groupList = getSqlSession().selectList("SpaceManage.getGroupParticipant", mapper);
    return groupList;
}


public int entryReject(int spaceJoinIdx){
    return getSqlSession().update("SpaceManage.entryReject", spaceJoinIdx);
}


public List<Map<String,String>> getRecentFileList(int spaceIdx){
    List<Map<String, String>> list;
    list = getSqlSession().selectList("SpaceManage.getRecentFile", spaceIdx);
    return list;
}


@SuppressWarnings("unchecked")
public List<Map<String,String>> getMySpaceList(int userIdx){
    List<Map<String, String>> userList = null;
    userList = getSqlSession().selectList("SpaceManage.getMySpaceList", userIdx);
    return userList;
}


public List<Map<String,String>> getMyEntryRequestedList(int spaceIdx){
    List<Map<String, String>> list;
    list = getSqlSession().selectList("SpaceManage.getMyEntryRequestedList", spaceIdx);
    return list;
}


@SuppressWarnings("unchecked")
public List<Map<String,String>> getMyEntryList(int userIdx){
    List<Map<String, String>> userList = null;
    userList = getSqlSession().selectList("SpaceManage.getMyEntryList", userIdx);
    return userList;
}


public int entryApprove(int spaceJoinIdx,WeSpaceUser weSpaceUser){
    getSqlSession().update("SpaceManage.entryApprove", spaceJoinIdx);
    return getSqlSession().insert("SpaceManage.entrySpaceUser", weSpaceUser);
}


public Integer checkUserIntoMe(int spaceIdx,int userIdx,String authorityType){
    Integer checkUser;
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("spaceIdx", spaceIdx);
    mapper.put("userIdx", userIdx);
    mapper.put("tranType", authorityType);
    checkUser = (Integer) getSqlSession().selectOne("SpaceManage.checkUserIntoMe", mapper);
    return checkUser;
}


@SuppressWarnings("unchecked")
public List<Map<String,String>> getRecentSpaceList(int userIdx,Integer startRow,Integer endRow){
    List<Map<String, String>> userList = null;
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("userIdx", userIdx);
    mapper.put("startRow", startRow);
    mapper.put("endRow", endRow);
    userList = getSqlSession().selectList("SpaceManage.getRecentSpaceList", mapper);
    return userList;
}


public List<Map<String,Object>> groupSelectedList(int spaceIdx){
    List<Map<String, Object>> groupSelectedList = null;
    groupSelectedList = getSqlSession().selectList("SpaceManage.getMyGroupAuthorityList", spaceIdx);
    return groupSelectedList;
}


public List<Map<String,String>> getMyInviteList(int spaceIdx){
    List<Map<String, String>> list;
    list = getSqlSession().selectList("SpaceManage.getMyInviteList", spaceIdx);
    return list;
}


@SuppressWarnings("unchecked")
public List<Map<String,Object>> getUserParticipant(String tranType,int spaceIdx){
    List<Map<String, Object>> userList = null;
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("spaceIdx", spaceIdx);
    mapper.put("tranType", tranType);
    userList = getSqlSession().selectList("SpaceManage.getUserParticipant", mapper);
    return userList;
}


public List<Map<String,Object>> userSelectedList(int spaceIdx){
    List<Map<String, Object>> userSelectedList = null;
    userSelectedList = getSqlSession().selectList("SpaceManage.getMyUserAuthorityList", spaceIdx);
    return userSelectedList;
}


public List<Map<String,Object>> checkGroupIntoMe(int spaceIdx,int userIdx,String authorityType){
    List<Map<String, Object>> checkGroup = null;
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("spaceIdx", spaceIdx);
    mapper.put("userIdx", userIdx);
    mapper.put("tranType", authorityType);
    checkGroup = getSqlSession().selectList("SpaceManage.checkGroupIntoMe", mapper);
    return checkGroup;
}


public SpaceInfoVo getSpaceInfo(int spaceIdx){
    SpaceInfoVo spaceInfoVo = null;
    spaceInfoVo = (SpaceInfoVo) getSqlSession().selectOne("SpaceManage.getSpaceInfo", spaceIdx);
    return spaceInfoVo;
}


@SuppressWarnings("unchecked")
public List<Map<String,String>> getAllSpaceList(int userIdx,int weGrade){
    List<Map<String, String>> userList = null;
    if (weGrade < 9) {
        userList = getSqlSession().selectList("SpaceManage.getAllSpaceList", userIdx);
    } else {
        userList = getSqlSession().selectList("SpaceManage.getAllSpaceListByAdmin", userIdx);
    }
    return userList;
}


public List<Map<String,String>> getUpdatedList(int spaceIdx,Integer startRow,Integer endRow){
    List<Map<String, String>> list;
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("spaceIdx", spaceIdx);
    mapper.put("startRow", startRow);
    mapper.put("endRow", endRow);
    list = getSqlSession().selectList("MainManage.getUpdatedList", mapper);
    return list;
}


}