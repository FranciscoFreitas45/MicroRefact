package cn.maxcj.modular.system.dao;
 import cn.maxcj.modular.system.model.Menu;
import cn.maxcj.core.common.node.MenuNode;
import cn.maxcj.core.common.node.ZTreeNode;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
public interface MenuMapper extends BaseMapper<Menu>{


public List<MenuNode> getMenusByRoleIds(List<Integer> roleIds)
;

public List<ZTreeNode> menuTreeListByMenuIds(List<Long> menuIds)
;

public List<ZTreeNode> menuTreeList()
;

public List<Long> getMenuIdsByRoleId(Integer roleId)
;

public int deleteRelationByMenu(Long menuId)
;

public List<String> getResUrlsByRoleId(Integer roleId)
;

public List<Map<String,Object>> selectMenus(String condition,String level)
;

}