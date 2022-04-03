package cn.gson.oasys.model.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import cn.gson.oasys.model.entity.system.SystemMenu;
@Repository
public interface IndexDao extends JpaRepository<SystemMenu, Long>{


public List<SystemMenu> findByParentIdOrderBySortId(Long parentId)
;

@Query("update SystemMenu menu set menu.sortId=(:sortId) where menu.parentId = :parentId and menu.sortId=(:sortId - :arithNum)")
@Modifying
public int changeSortId(Integer sortId,Integer arithNum,Long parentId)
;

public List<SystemMenu> findByParentIdAndShowOrderBySortId(Long parentId,Boolean boo)
;

public List<SystemMenu> findByParentIdNotOrderBySortId(Long parentId)
;

@Query("update SystemMenu menu set menu.parentId=999 where menu.menuId= :menuId")
@Modifying
public int deleteThis(Long menuId)
;

public List<SystemMenu> findByMenuNameLike(String name)
;

public List<SystemMenu> findByParentIdNotAndShowOrderBySortId(Long parentId,Boolean boo)
;

@Query("select sy from SystemMenu as sy ")
public List<SystemMenu> findall()
;

@Query("update SystemMenu menu set menu.sortId=(:sortId -:arithNum) where menu.menuId= :menuId")
@Modifying
public int changeSortId2(Integer sortId,Integer arithNum,Long menuId)
;

}