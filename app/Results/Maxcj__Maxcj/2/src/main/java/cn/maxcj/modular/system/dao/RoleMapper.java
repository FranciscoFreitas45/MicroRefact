package cn.maxcj.modular.system.dao;
 import cn.maxcj.modular.system.model.Role;
import cn.maxcj.core.common.node.ZTreeNode;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
public interface RoleMapper extends BaseMapper<Role>{


public List<ZTreeNode> roleTreeList()
;

public List<ZTreeNode> roleTreeListByRoleId(String[] roleId)
;

public int deleteRolesById(Integer roleId)
;

public List<Map<String,Object>> selectRoles(String condition)
;

}