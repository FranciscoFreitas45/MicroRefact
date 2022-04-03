package org.live.sys.repository;
 import org.live.common.base.BaseRepository;
import org.live.sys.entity.Menu;
import org.live.sys.vo.MenuNode;
import org.live.sys.vo.SidebarNode;
import org.live.sys.vo.ZtreeNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface MenuRepository extends BaseRepository<Menu, String>{


@Query(value = "select new org.live.sys.vo.MenuNode(m.id, m.menuName,m.parent.id,m.serialNo,m.menuType) from Menu m where m.parent.id=:parentId order by m.serialNo asc")
public List<MenuNode> findMenuNodeByParentId(String parentId)
;

@Query(value = "select distinct m.id from RolePermission pr,Menu m, Permission p where pr.permission.id=p.id and p.id=m.permission.id and pr.role.id=:roleId")
public List<String> findMenuIdByRoleId(String roleId)
;

@Query(value = "select distinct m from RolePermission rp, Menu m, Permission p where rp.permission.id=p.id and p.id=m.permission.id and rp.role.id=:roleId")
public List<Menu> findMenuByRoleId(String roleId)
;

@Query(value = "select m from Menu m order by m.serialNo asc")
public Page<Menu> findAllMenu(Pageable pageable)
;

@Query(value = "select distinct new org.live.sys.vo.MenuNode(m.id, m.menuName,m.parent.id,m.serialNo) from Menu m order by m.serialNo asc")
public List<MenuNode> findAllMenuNode()
;

@Query(value = "select count(*) from Menu m where m.parent.id=:parentId")
public long countChildMenuByParentId(String parentId)
;

public List<SidebarNode> findMenu4SidebarNodeByUserId(String userId)
;

@Query(value = "select new org.live.sys.vo.SidebarNode(m.id, m.menuName, m.menuUrl, m.menuIcon, m.serialNo, m.parent.id) from Menu m where m.id=:menuId")
public SidebarNode findMenu4sidebarNodeByMenuId(String menuId)
;

@Query(value = "select distinct new org.live.sys.vo.ZtreeNode(m.id, m.parent.id, m.menuName) from Menu m where m.id !='root' order by m.serialNo asc")
public List<ZtreeNode> findAllMenuNoParent4ztree()
;

public Menu getMenu(String idVM7Z);

public void setMenu(String idVM7Z,Menu menu);

}