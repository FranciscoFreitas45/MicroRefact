package com.lingxiang2014.dao.impl;
 import java.util.List;
import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.dao.AreaDao;
import com.lingxiang2014.entity.Area;
@Repository("areaDaoImpl")
public class AreaDaoImpl extends BaseDaoImpl<Area, Long>implements AreaDao{


public List<Area> findRoots(Integer count){
    String jpql = "select area from Area area where area.parent is null order by area.order asc";
    TypedQuery<Area> query = entityManager.createQuery(jpql, Area.class).setFlushMode(FlushModeType.COMMIT);
    if (count != null) {
        query.setMaxResults(count);
    }
    return query.getResultList();
}


}