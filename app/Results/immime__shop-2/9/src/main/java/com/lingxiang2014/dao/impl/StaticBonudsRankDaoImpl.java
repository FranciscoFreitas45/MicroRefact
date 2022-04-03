package com.lingxiang2014.dao.impl;
 import java.math.BigDecimal;
import java.util.List;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import com.lingxiang2014.dao.StaticBonudsRankDao;
import com.lingxiang2014.entity.StaticBonudsRank;
@Repository("staticBonudsRankDaoImpl")
public class StaticBonudsRankDaoImpl extends BaseDaoImpl<StaticBonudsRank, Long>implements StaticBonudsRankDao{


public StaticBonudsRank findDefault(){
    try {
        String jpql = "select staticBonudsRank from StaticBonudsRank staticBonudsRank where staticBonudsRank.isDefault = true";
        return entityManager.createQuery(jpql, StaticBonudsRank.class).setFlushMode(FlushModeType.COMMIT).getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}


public boolean everyAmountExists(BigDecimal everyAmount){
    if (everyAmount == null) {
        return false;
    }
    String jpql = "select count(*) from StaticBonudsRank staticBonudsRank where staticBonudsRank.everyAmount = :everyAmount";
    Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("everyAmount", everyAmount).getSingleResult();
    return count > 0;
}


public StaticBonudsRank findByAmount(BigDecimal amount){
    if (amount == null) {
        return null;
    }
    String jpql = "select staticBonudsRank from StaticBonudsRank staticBonudsRank where staticBonudsRank.isSpecial = false and staticBonudsRank.amount <= :amount order by staticBonudsRank.amount desc";
    return entityManager.createQuery(jpql, StaticBonudsRank.class).setFlushMode(FlushModeType.COMMIT).setParameter("amount", amount).setMaxResults(1).getSingleResult();
}


@Override
public StaticBonudsRank merge(StaticBonudsRank staticBonudsRank){
    Assert.notNull(staticBonudsRank);
    if (staticBonudsRank.getIsDefault()) {
        String jpql = "update StaticBonudsRank staticBonudsRank set staticBonudsRank.isDefault = false where staticBonudsRank.isDefault = true and staticBonudsRank != :staticBonudsRank";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("staticBonudsRank", staticBonudsRank).executeUpdate();
    }
    return super.merge(staticBonudsRank);
}


public boolean amountExists(BigDecimal amount){
    if (amount == null) {
        return false;
    }
    String jpql = "select count(*) from StaticBonudsRank staticBonudsRank where staticBonudsRank.amount = :amount";
    Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("amount", amount).getSingleResult();
    return count > 0;
}


public boolean nameExists(String name){
    if (name == null) {
        return false;
    }
    String jpql = "select count(*) from StaticBonudsRank staticBonudsRank where lower(staticBonudsRank.name) = lower(:name)";
    Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("name", name).getSingleResult();
    return count > 0;
}


@Override
public void persist(StaticBonudsRank staticBonudsRank){
    Assert.notNull(staticBonudsRank);
    if (staticBonudsRank.getIsDefault()) {
        String jpql = "update StaticBonudsRank staticBonudsRank set staticBonudsRank.isDefault = false where staticBonudsRank.isDefault = true";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).executeUpdate();
    }
    super.persist(staticBonudsRank);
}


public List<StaticBonudsRank> findLTEQ(Integer myPeople){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<StaticBonudsRank> criteriaQuery = criteriaBuilder.createQuery(StaticBonudsRank.class);
    Root<StaticBonudsRank> root = criteriaQuery.from(StaticBonudsRank.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();
    if (myPeople != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThanOrEqualTo(root.<Double>get("scale"), myPeople * 1.0));
    }
    criteriaQuery.where(restrictions);
    criteriaQuery.orderBy(criteriaBuilder.desc(root.get("scale")));
    return super.findList(criteriaQuery, null, null, null, null);
}


@Override
public void remove(StaticBonudsRank staticBonudsRank){
    if (staticBonudsRank != null && !staticBonudsRank.getIsDefault()) {
        super.remove(super.merge(staticBonudsRank));
    }
}


}