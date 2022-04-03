package com.lingxiang2014.dao.impl;
 import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.TransferDao;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Transfer;
import com.lingxiang2014.entity.Transfer.Method;
@Repository("transferDaoImpl")
public class TransferDaoImpl extends BaseDaoImpl<Transfer, Long>implements TransferDao{


public Page<Transfer> findPage(Member fromMember,Member toMember,Method method,Pageable pageable){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Transfer> criteriaQuery = criteriaBuilder.createQuery(Transfer.class);
    Root<Transfer> root = criteriaQuery.from(Transfer.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();
    if (fromMember != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("fromMember"), fromMember));
    }
    if (toMember != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("toMember"), toMember));
    }
    if (method != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("method"), method));
    }
    criteriaQuery.where(restrictions);
    return super.findPage(criteriaQuery, pageable);
}


}