package cn.maxcj.modular.system.service.impl;
 import cn.hutool.core.convert.Convert;
import cn.maxcj.core.common.node.ZTreeNode;
import cn.maxcj.modular.system.dao.RelationMapper;
import cn.maxcj.modular.system.dao.RoleMapper;
import cn.maxcj.modular.system.model.Relation;
import cn.maxcj.modular.system.model.Role;
import cn.maxcj.modular.system.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>implements IRoleService{

@Resource
 private  RoleMapper roleMapper;

@Resource
 private  RelationMapper relationMapper;


@Override
@Transactional(rollbackFor = Exception.class)
public void setAuthority(Integer roleId,String ids){
    // 删除该角色所有的权限
    this.roleMapper.deleteRolesById(roleId);
    // 添加新的权限
    for (Long id : Convert.toLongArray(ids.split(","))) {
        Relation relation = new Relation();
        relation.setRoleid(roleId);
        relation.setMenuid(id);
        this.relationMapper.insert(relation);
    }
}


@Override
public List<ZTreeNode> roleTreeList(){
    return this.baseMapper.roleTreeList();
}


@Override
public List<ZTreeNode> roleTreeListByRoleId(String[] roleId){
    return this.baseMapper.roleTreeListByRoleId(roleId);
}


@Override
public int deleteRolesById(Integer roleId){
    return this.baseMapper.deleteRolesById(roleId);
}


@Override
@Transactional(rollbackFor = Exception.class)
public void delRoleById(Integer roleId){
    // 删除角色
    this.roleMapper.deleteById(roleId);
    // 删除该角色所有的权限
    this.roleMapper.deleteRolesById(roleId);
}


@Override
public List<Map<String,Object>> selectRoles(String condition){
    return this.baseMapper.selectRoles(condition);
}


}