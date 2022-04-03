package org.gliderwiki.admin.service;
 import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeGroupUser;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.GroupUserVo;
public interface AdminGroupService {


public List<GroupUserVo> getGroupUserList(Integer groupIdx)
;

public Object[] getUserProfileInfo(Integer weUserIdx,String attrId)
;

public String updateGroupInfoDWR(Integer groupIdx,String groupName,String groupType,String groupInfo,String adminIdx,String userIdx)
;

public String deleteGroupInfoDWR(Integer weGroupIdx,Integer userIdx)
;

public int arrayInsertGroupUser(String arrayCheckId,String checkGroupIdx)
;

public String deleteGroupInUserDWR(Integer weGroupIdx,String checkUsersId,Integer userIdx)
;

public int insertGroupInfo(Map<Integer,Map> maps)
;

public String insertGroupInfoDWR(String groupName,String groupType,String groupInfo,String adminIdx,String userIdx)
;

public List<WeUser> getWeAdminList()
;

public Object[] getGroupInfo(Integer weGroupIdx)
;

public List<WeUser> getWeUserList(Integer loginUserIdx,String userNick,String userEmail,String userName)
;

public int updateUserProfileInfo(Integer weUserIdx,Integer weGrade,String weTechYn,Integer wePoint)
;

public List<WeGroupInfo> getGroupInfoList()
;

public int insertGroupUser(Map<Integer,Map> maps)
;

}