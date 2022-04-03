package com.lingxiang2014.dao.impl;
 import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.dao.SeoDao;
import com.lingxiang2014.entity.Seo;
import com.lingxiang2014.entity.Seo.Type;
@Repository("seoDaoImpl")
public class SeoDaoImpl extends BaseDaoImpl<Seo, Long>implements SeoDao{


public Seo find(Type type){
    if (type == null) {
        return null;
    }
    try {
        String jpql = "select seo from Seo seo where seo.type = :type";
        return entityManager.createQuery(jpql, Seo.class).setFlushMode(FlushModeType.COMMIT).setParameter("type", type).getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}


}