package com.lingxiang2014.dao.impl;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.BSystemAccountDao;
import com.lingxiang2014.entity.BSystemAccount;
@Repository("bSystemAccountDaoImpl")
public class BSystemAccountDaoImpl extends BaseDaoImpl<BSystemAccount, Long>implements BSystemAccountDao{


public boolean usernameExists(String username){
    if (username == null) {
        return false;
    }
    String jpql = "select count(*) from BSystemAccount bSystemAccounts where lower(bSystemAccounts.username) = lower(:username)";
    Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
    return count > 0;
}


public Page<BSystemAccount> findChildrenPage(BSystemAccount bSystemAccount,Pageable pageable){
    if (bSystemAccount == null) {
        return new Page<BSystemAccount>(Collections.<BSystemAccount>emptyList(), 0, pageable);
    }
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<BSystemAccount> criteriaQuery = criteriaBuilder.createQuery(BSystemAccount.class);
    Root<BSystemAccount> root = criteriaQuery.from(BSystemAccount.class);
    criteriaQuery.select(root);
    criteriaQuery.where(criteriaBuilder.equal(root.get("parent"), bSystemAccount));
    return super.findPage(criteriaQuery, pageable);
}


public BSystemAccount findByUsername(String username){
    if (username == null) {
        return null;
    }
    try {
        String jpql = "select bSystemAccounts from BSystemAccount bSystemAccounts where lower(bSystemAccounts.username) = lower(:username)";
        return entityManager.createQuery(jpql, BSystemAccount.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}


public BSystemAccount findLeaf(Integer index){
    BSystemAccount leaf = null;
    if (index == null) {
        index = 0;
    }
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<BSystemAccount> criteriaQuery = criteriaBuilder.createQuery(BSystemAccount.class);
    Root<BSystemAccount> root = criteriaQuery.from(BSystemAccount.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();
    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isLeaf"), true));
    criteriaQuery.where(restrictions);
    List<com.lingxiang2014.Order> orders = new ArrayList<com.lingxiang2014.Order>();
    orders.add(com.lingxiang2014.Order.asc("id"));
    List<BSystemAccount> list = super.findList(criteriaQuery, null, null, null, orders);
    if (list == null || list.size() == 0) {
        leaf = null;
    } else if (list.size() < index) {
        leaf = list.get(list.size() - 1);
    } else {
        leaf = list.get(index - 1);
    }
    return leaf;
}


public List<BSystemAccount> findListByEmail(String email){
    if (email == null) {
        return Collections.<BSystemAccount>emptyList();
    }
    String jpql = "select bSystemAccounts from BSystemAccount bSystemAccounts where lower(bSystemAccounts.email) = lower(:email)";
    return entityManager.createQuery(jpql, BSystemAccount.class).setFlushMode(FlushModeType.COMMIT).setParameter("email", email).getResultList();
}


public boolean emailExists(String email){
    if (email == null) {
        return false;
    }
    String jpql = "select count(*) from BSystemAccount bSystemAccounts where lower(bSystemAccounts.email) = lower(:email)";
    Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("email", email).getSingleResult();
    return count > 0;
}


}