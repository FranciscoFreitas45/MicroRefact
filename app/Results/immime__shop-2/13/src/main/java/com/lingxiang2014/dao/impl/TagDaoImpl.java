package com.lingxiang2014.dao.impl;
 import java.util.List;
import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.dao.TagDao;
import com.lingxiang2014.entity.Tag;
import com.lingxiang2014.entity.Tag.Type;
@Repository("tagDaoImpl")
public class TagDaoImpl extends BaseDaoImpl<Tag, Long>implements TagDao{


public List<Tag> findList(Type type){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
    Root<Tag> root = criteriaQuery.from(Tag.class);
    criteriaQuery.select(root);
    if (type != null) {
        criteriaQuery.where(criteriaBuilder.equal(root.get("type"), type));
    }
    criteriaQuery.orderBy(criteriaBuilder.asc(root.get("order")));
    return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
}


}