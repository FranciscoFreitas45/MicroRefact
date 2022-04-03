package org.live.sys.repository;
 import org.live.common.base.BaseRepository;
import org.live.sys.entity.PageElement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface PageElementRepository extends BaseRepository<PageElement, String>{


@Query(value = "select count(*) from PageElement p where p.menu.id=:menuId")
public long countPageElementByMenuId(String menuId)
;

}