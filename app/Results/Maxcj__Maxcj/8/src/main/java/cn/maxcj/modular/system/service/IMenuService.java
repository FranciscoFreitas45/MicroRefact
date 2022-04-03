package cn.maxcj.modular.system.service;
 import cn.maxcj.core.common.node.MenuNode;
import cn.maxcj.core.common.node.ZTreeNode;
import cn.maxcj.modular.system.model.Menu;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
public interface IMenuService extends IService<Menu>{


public List<MenuNode> getMenusByRoleIds(List<Integer> roleIds)
;

public List<ZTreeNode> menuTreeListByMenuIds(List<Long> menuIds)
;

public List<ZTreeNode> menuTreeList()
;

public void delMenu(Long menuId)
;

public List<Long> getMenuIdsByRoleId(Integer roleId)
;

public int deleteRelationByMenu(Long menuId)
;

public List<String> getResUrlsByRoleId(Integer roleId)
;

public void delMenuContainSubMenus(Long menuId)
;

public List<Map<String,Object>> selectMenus(String condition,String level)
;

}