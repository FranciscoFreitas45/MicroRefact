package org.gliderwiki.admin.dao;
 import java.util.List;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeGroupUser;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.GroupUserVo;
public interface AdminGroupDao {


public List<GroupUserVo> getGroupUserList(GroupUserVo groupUser)
;

public int insertGroupInfo(WeGroupInfo weGroupInfo)
;

public int deleteUserInGroupDWR(WeGroupUser groupUser)
;

public int deleteGroupInfoDWR(WeGroupInfo domain)
;

public List<WeUser> getSearchAdminList()
;

public List<WeGroupInfo> getGroupInfoList()
;

public int insertGroupUser(WeGroupUser weGroupUser)
;

public List<WeGroupInfo> getUserJoinGroupList(Integer weUserIdx)
;

public int deleteGroupUserDWR(WeGroupUser domain)
;

}