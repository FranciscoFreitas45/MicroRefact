package com.lingxiang2014.dao.impl;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.BankDao;
import com.lingxiang2014.entity.Bank;
import com.lingxiang2014.entity.Member;
@Repository("bankDaoImpl")
public class BankDaoImpl extends BaseDaoImpl<Bank, Long>implements BankDao{


public List<Bank> findListByMember(Member member){
    if (member == null) {
        return new ArrayList<Bank>();
    }
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Bank> criteriaQuery = criteriaBuilder.createQuery(Bank.class);
    Root<Bank> root = criteriaQuery.from(Bank.class);
    criteriaQuery.select(root);
    criteriaQuery.where(criteriaBuilder.equal(root.join("member"), member));
    return super.findList(criteriaQuery, null, null, null, null);
}


public Page<Bank> findPage(Pageable pageable,Member member){
    if (member == null) {
        return new Page<Bank>(Collections.<Bank>emptyList(), 0, pageable);
    }
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Bank> criteriaQuery = criteriaBuilder.createQuery(Bank.class);
    Root<Bank> root = criteriaQuery.from(Bank.class);
    criteriaQuery.select(root);
    criteriaQuery.where(criteriaBuilder.equal(root.join("member"), member));
    return super.findPage(criteriaQuery, pageable);
}


}