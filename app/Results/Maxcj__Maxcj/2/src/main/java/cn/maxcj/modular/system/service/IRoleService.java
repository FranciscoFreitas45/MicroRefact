package cn.maxcj.modular.system.service;
 import cn.maxcj.core.common.node.ZTreeNode;
import cn.maxcj.modular.system.model.Role;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
public interface IRoleService extends IService<Role>{


public void setAuthority(Integer roleId,String ids)
;

public List<ZTreeNode> roleTreeList()
;

public List<ZTreeNode> roleTreeListByRoleId(String[] roleId)
;

public int deleteRolesById(Integer roleId)
;

public void delRoleById(Integer roleId)
;

public List<Map<String,Object>> selectRoles(String condition)
;

}