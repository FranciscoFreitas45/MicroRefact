package com.lingxiang2014.dao.impl;
 import java.util.List;
import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.dao.FriendLinkDao;
import com.lingxiang2014.entity.FriendLink;
import com.lingxiang2014.entity.FriendLink.Type;
@Repository("friendLinkDaoImpl")
public class FriendLinkDaoImpl extends BaseDaoImpl<FriendLink, Long>implements FriendLinkDao{


public List<FriendLink> findList(Type type){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<FriendLink> criteriaQuery = criteriaBuilder.createQuery(FriendLink.class);
    Root<FriendLink> root = criteriaQuery.from(FriendLink.class);
    criteriaQuery.select(root);
    if (type != null) {
        criteriaQuery.where(criteriaBuilder.equal(root.get("type"), type));
    }
    criteriaQuery.orderBy(criteriaBuilder.asc(root.get("order")));
    return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
}


}