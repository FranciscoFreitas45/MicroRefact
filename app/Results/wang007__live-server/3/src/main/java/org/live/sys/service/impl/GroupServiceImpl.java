package org.live.sys.service.impl;
 import org.live.common.base.BaseRepository;
import org.live.common.base.BaseServiceImpl;
import org.live.common.exception.ServiceException;
import org.live.common.utils.CopyPropertiesUtils;
import org.live.sys.entity.Group;
import org.live.sys.entity.GroupRole;
import org.live.sys.entity.Role;
import org.live.sys.repository.GroupRepository;
import org.live.sys.repository.GroupRoleRepository;
import org.live.sys.repository.RoleRepository;
import org.live.sys.service.GroupService;
import org.live.sys.vo.FindGroupVo;
import org.live.sys.vo.GroupInfo;
import org.live.sys.vo.GroupVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service("groupService")
@Transactional(readOnly = true)
public class GroupServiceImpl extends BaseServiceImpl<Group, String>implements GroupService{

@Resource
 private  GroupRepository groupRepo;

@Resource
 private  GroupRoleRepository grRepo;

@Resource
 private  RoleRepository roleRepo;


@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
@Override
public void updateGroupInfo(GroupVo groupVo){
    if (groupVo != null) {
        Group group = this.findOne(groupVo.getId());
        if (group == null)
            return;
        CopyPropertiesUtils.copyPropertiesIgnoreNull(group, groupVo);
        this.save(group);
        String[] roleIds = groupVo.getRoleIds();
        if (roleIds != null) {
            // 需要被处理的角色id,即客户端传过来的数据
            List<String> needHandleRoles = Arrays.asList(roleIds);
            // 临时保存删除关联关系中的角色id
            List<String> deleteRoles = new ArrayList<String>();
            // 临时保存添加关联关系中的角色id
            List<String> alreadyHaveRoles = new ArrayList<String>();
            // 数据库中与group关联的角色
            List<Role> rolesInPersistent = this.grRepo.findRoleByGroup(group);
            if (rolesInPersistent != null) {
                for (Role role : rolesInPersistent) {
                    if (!needHandleRoles.contains(role.getId())) {
                        // 需要被处理的角色中不存在数据库查出来的角色，那么就删除。
                        // 
                        deleteRoles.add(role.getId());
                    }
                    alreadyHaveRoles.add(role.getId());
                }
            }
            for (String deleteRole : deleteRoles) {
                // 删除用户组与角色的关联关系
                this.grRepo.deleteGroupRoleByRoleId(group.getId(), deleteRole);
            }
            for (String needHandleRole : needHandleRoles) {
                // 添加用户组与角色的关联关系
                if (!alreadyHaveRoles.contains(needHandleRole)) {
                    // 在数据库不存在这个关联关系
                    Role roleInPersistent = this.roleRepo.findOne(needHandleRole);
                    if (roleInPersistent != null) {
                        this.grRepo.save(new GroupRole(roleInPersistent, group));
                    }
                }
            }
        } else {
            // 删除全部用户组与角色的关联关系
            this.grRepo.deleteGroupRoleByGroupId(groupVo.getId());
        }
    }
}


@Override
public Integer createSerialNo(){
    Integer csn;
    // 获得serialNo最大值
    csn = this.groupRepo.getMaxSerialNo();
    if (csn != null) {
        // 非空则最大值+1,空则为1
        csn = csn + 1;
    } else {
        csn = 1;
    }
    return csn;
}


@Override
public BaseRepository<Group,String> getRepository(){
    return this.groupRepo;
}


@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
@Override
public void saveGroupInfo(Group group,String[] roleIds){
    group = this.save(group);
    if (roleIds != null) {
        for (String roleId : roleIds) {
            Role role = this.roleRepo.findOne(roleId);
            if (role != null) {
                this.grRepo.save(new GroupRole(role, group));
            }
        }
    }
}


@Override
public GroupInfo findGorupInfoByGroupId(String groupId){
    GroupInfo info = new GroupInfo();
    final Group group = this.findOne(groupId);
    info.setGroup(group);
    if (group != null) {
        List<Role> roles = this.grRepo.findRoleByGroup(group);
        info.setRoles(roles);
    }
    return info;
}


public boolean isExistSerialNo(int serialNo){
    List<Group> groups = this.findGroupBySerialNo(serialNo);
    return groups != null && groups.size() > 0;
}


@Override
public Page<FindGroupVo> findGroups(Pageable pageable,Group group){
    Page<Group> pageGroups = this.groupRepo.findGroups(pageable, group);
    List<FindGroupVo> groupVos = null;
    if (pageGroups != null) {
        groupVos = new ArrayList<FindGroupVo>(pageGroups.getNumberOfElements());
        List<Group> groups = pageGroups.getContent();
        if (groups != null) {
            for (Group g : groups) {
                FindGroupVo vo = new FindGroupVo();
                CopyPropertiesUtils.copyPropertiesIgnoreNull(vo, g);
                // 角色名
                String[] roleNames = this.grRepo.findRoleNamesByGroupId(g.getId());
                vo.setRoleNames(roleNames);
                groupVos.add(vo);
            }
        }
        return new PageImpl<FindGroupVo>(groupVos, pageable, pageGroups.getTotalElements());
    }
    return null;
}


@Override
public List<Group> findGroupBySerialNo(int serialNo){
    List<Group> groupinfos = null;
    try {
        groupinfos = this.groupRepo.findGroupByGroupSerialNo(serialNo);
    } catch (Exception e) {
        throw new ServiceException(e);
    }
    return groupinfos;
}


@Override
public List<GroupInfo> findGroupInfos(){
    List<GroupInfo> infos = null;
    List<Group> groups = this.groupRepo.findAll();
    if (groups != null & groups.size() > 0) {
        infos = new ArrayList<GroupInfo>(groups.size());
        for (Group group : groups) {
            List<Role> roles = this.grRepo.findRoleByGroup(group);
            infos.add(new GroupInfo(group, roles));
        }
    }
    return infos;
}


}