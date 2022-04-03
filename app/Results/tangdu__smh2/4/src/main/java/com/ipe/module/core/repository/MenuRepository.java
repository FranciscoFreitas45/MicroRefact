package com.ipe.module.core.repository;
 import com.ipe.common.dao.CustomerRepository;
import com.ipe.module.core.entity.Menu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface MenuRepository extends CustomerRepository<Menu, String>{


@Query("select max(sno)+1 from Menu")
public int getMaxSno()
;

@Query("from Menu m where m.parent.id=:pid")
public List<Menu> getParentMenu(String pid)
;

@Modifying
@Query("update Menu set parent.id=:pid,sno=:sno where id=:id")
public void updateParent(String pid,int sno,String id)
;

}