package cn.gson.oasys.model.dao.roledao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import cn.gson.oasys.model.entity.role.Rolemenu;
import cn.gson.oasys.model.entity.role.Rolepowerlist;
public interface RolepowerlistDao extends JpaRepository<Rolepowerlist, Long>{


@Query("select new cn.gson.oasys.model.entity.role.Rolemenu(menu.menuId,menu.menuName,menu.menuUrl,menu.show,role.check,menu.parentId,menu.menuIcon,menu.sortId,menu.menuGrade) " + "from Rolepowerlist as role,SystemMenu as menu where role.menuId.menuId=menu.menuId " + "and menu.parentId=?1 and role.roleId.roleId=?2 and menu.show=?3 and role.check=?4 order by menu.sortId")
public List<Rolemenu> findbyparentxianall(Long id,Long roleid,Boolean bo,Boolean le)
;

@Query("select new cn.gson.oasys.model.entity.role.Rolemenu(menu.menuId,menu.menuName,menu.menuUrl,menu.show,role.check,menu.parentId,menu.menuIcon,menu.sortId,menu.menuGrade) " + "from Rolepowerlist as role,SystemMenu as menu where role.menuId.menuId=menu.menuId " + "and menu.parentId!=?1 and role.roleId.roleId=?2 order by menu.sortId")
public List<Rolemenu> findbyparents(Long id,Long roleid)
;

@Query("select new cn.gson.oasys.model.entity.role.Rolemenu(menu.menuId,menu.menuName,menu.menuUrl,menu.show,role.check,menu.parentId,menu.menuIcon,menu.sortId,menu.menuGrade) " + "from Rolepowerlist as role,SystemMenu as menu where role.menuId.menuId=menu.menuId " + "and menu.parentId=?1 and role.roleId.roleId=?2 order by menu.sortId")
public List<Rolemenu> findbyparentall(Long id,Long roleid)
;

@Query("select po from Rolepowerlist as po where po.roleId.roleId=?1 and po.menuId.menuId=?2")
public Rolepowerlist findbyroleidandmenuid(Long roleid,Long menuid)
;

@Query("select new cn.gson.oasys.model.entity.role.Rolemenu(menu.menuId,menu.menuName,menu.menuUrl,menu.show,role.check,menu.parentId,menu.menuIcon,menu.sortId,menu.menuGrade) " + "from Rolepowerlist as role,SystemMenu as menu where role.menuId.menuId=menu.menuId " + "and menu.parentId=?1 and role.roleId.roleId=?2 and menu.show=?3 and role.check=?4 and menu.menuName like %?5% order by menu.sortId")
public List<Rolemenu> findname(Long id,Long roleid,Boolean bo,Boolean le,String name)
;

@Query("select new cn.gson.oasys.model.entity.role.Rolemenu(menu.menuId,menu.menuName,menu.menuUrl,menu.show,role.check,menu.parentId,menu.menuIcon,menu.sortId,menu.menuGrade) " + "from Rolepowerlist as role,SystemMenu as menu where role.menuId.menuId=menu.menuId " + "and menu.parentId!=?1 and role.roleId.roleId=?2 and menu.show=?3 and role.check=?4 order by menu.sortId")
public List<Rolemenu> findbyparentsxian(Long id,Long roleid,Boolean bo,Boolean le)
;

}