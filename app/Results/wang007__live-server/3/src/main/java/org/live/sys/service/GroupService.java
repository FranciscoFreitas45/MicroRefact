package org.live.sys.service;
 import org.live.common.base.BaseService;
import org.live.sys.entity.Group;
import org.live.sys.vo.FindGroupVo;
import org.live.sys.vo.GroupInfo;
import org.live.sys.vo.GroupVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface GroupService extends BaseService<Group, String>{


public void updateGroupInfo(GroupVo groupVo)
;

public Integer createSerialNo()
;

public void saveGroupInfo(Group group,String[] roleIds)
;

public GroupInfo findGorupInfoByGroupId(String groupId)
;

public Page<FindGroupVo> findGroups(Pageable pageable,Group group)
;

public boolean isExistSerialNo(int serialNo)
;

public List<Group> findGroupBySerialNo(int serialNo)
;

public List<GroupInfo> findGroupInfos()
;

}