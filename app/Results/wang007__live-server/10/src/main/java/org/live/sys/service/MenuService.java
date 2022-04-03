package org.live.sys.service;
 import org.live.common.base.BaseService;
import org.live.sys.entity.Menu;
import org.live.sys.vo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface MenuService extends BaseService<Menu, String>{


public MenuNode getAlreadyAssembleMenuNodeTree()
;

public List<TreeViewNode> loadTreeViewNodesByParentId(String parentId)
;

public void deleteMenuById(String menuId)
;

public List<ZtreeNode> getZtreeNodesByRoles(String roleId)
;

public void saveMenuRoleRel(String roleId,String[] menuIds)
;

public Page<Menu> findAllMenu(Pageable pageable)
;

public void findChildNodesByParentId(String parentId,MenuNode rootMenuNode,MenuNode dumpMenuNode)
;

public Menu getAlreadyAssembleMenuTree()
;

public void saveMenuInfo(MenuVo menuVo)
;

public void updateMenuInfo(MenuVo menuVo)
;

public List<SidebarNode> findSidebarNodesByUserId(String userId)
;

public List<ZtreeNode> getAllZtreeNodes()
;

}